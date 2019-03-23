package br.com.fiap.orderservice.controller;

import br.com.fiap.orderservice.model.Order;
import br.com.fiap.orderservice.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderRepository repository;

    @GetMapping("/{id}")
    public ResponseEntity<Order> findById(@PathVariable("id") Long id){

        final Order order = repository.getById(id);

        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Order> save(@RequestBody Order order){

        repository.save(order);

        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> update(@PathVariable("id") Long id, @RequestBody Order order){

        final Order savedOrder = repository.getById(id);

        repository.delete(savedOrder);

        BeanUtils.copyProperties(order, savedOrder);

        repository.save(savedOrder);

        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Order> delete(@PathVariable("id") Long id){

        final Order savedOrder = repository.getById(id);

        repository.delete(savedOrder);

        return new ResponseEntity(HttpStatus.OK);
    }

}
