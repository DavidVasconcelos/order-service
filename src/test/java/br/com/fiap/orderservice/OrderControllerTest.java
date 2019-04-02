package br.com.fiap.orderservice;

import br.com.fiap.orderservice.controller.OrderController;
import br.com.fiap.orderservice.model.*;
import br.com.fiap.orderservice.repository.OrderRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private OrderRepository repository;

    @Test
    public void notFoundOrder() throws Exception {
        final String id = "1";
        mvc.perform(get("/order/" + 1)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void orderFounded() throws Exception {
        final Order order = getOrder();
        when(this.repository.getById(1L)).thenReturn(order);
        mvc.perform(get("/orders/" + order.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(response -> {
                    String json = response.getResponse().getContentAsString();
                    Order orderFounded = new ObjectMapper().readValue(json, Order.class);
                    Assertions.assertThat(order).isEqualToComparingFieldByField(orderFounded);
                });
    }



    @Test
    public void insertOrder() throws Exception {
        final Order order = getOrder();
        when(this.repository.getById(1L)).thenReturn(Optional.empty());
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        String jsonInString = mapper.writeValueAsString(order);
        mvc.perform(post("/orders")
                .accept(MediaType.APPLICATION_JSON))
                .content(jsonInString))
                .andExpect(status().isCreated());
    }


    private Order getOrder() {
        final Transaction transaction = new Transaction(1165454646464645654L,
                "5488888888887192", "12/23", Brand.VISA);
        final Item item = new Item("Item 1", new BigDecimal(10.13), 2);
        final List<Item> itens = Stream.of(item).collect(Collectors.toList());
        return new Order(1L, "Name", "email@teste.com.br", "Rua 1", itens,
                new BigDecimal(10.13), PaymentMode.CREDIT_CARD, Calendar.getInstance().getTime(), Status.COMPLETED,
                transaction);
    }


}