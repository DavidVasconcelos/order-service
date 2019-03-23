package br.com.fiap.orderservice.repository;

import br.com.fiap.orderservice.model.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderRepository {

    private static List<Order> orders = new ArrayList<>();

    public Order getById(Long id) {
        return orders.stream().filter(order -> order.getId() == id).findFirst().get();
    }

    public void save(Order order) {
        orders.add(order);
    }

    public void delete(Order order) {
        orders.remove(order);
    }

}
