package br.com.fiap.orderservice.repository;

import br.com.fiap.orderservice.model.Order;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderRepository {

    private static List<Order> orders = new ArrayList<>();

    private static Long id = 0L;

    public Order getById(Long id) {
        return orders.stream().filter(order -> order.getId() == id).findFirst().get();
    }

    public void save(Order order) {
        this.id = this.id + 1;
        order.setId(this.id);
        orders.add(order);
    }

    public void delete(Order order) {
        orders.remove(order);
    }

}
