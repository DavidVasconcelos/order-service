package br.com.fiap.orderservice.controller;

import br.com.fiap.orderservice.model.Order;
import br.com.fiap.orderservice.repository.OrderRepository;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;

@Slf4j
@RestController
@RequestMapping("/orders")
@Api(value = "Order", description = "Order Service REST API")
public class OrderController {

    @Autowired
    private OrderRepository repository;

    @ApiOperation(httpMethod = "GET", value = "Método get para buscar pedido filtrando por id")
            @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna um Order com uma mensagem de sucesso", response = Order.class)})
    @GetMapping("/{id}")
    public ResponseEntity<Order> findById(@ApiParam( value = "Order Id", required = true)
                                          @PathVariable("id") Long id) {

        final Order order = repository.getById(id);

        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @ApiOperation(httpMethod = "Post", value = "Método post inserir um pedido")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Retorna o path para pesquisa")})
    @PostMapping
    public ResponseEntity<Order> save(@Valid @RequestBody Order order) {

        final Order savedOrder = repository.save(order);

        URI location = getUri(savedOrder);

        return ResponseEntity.created(location).build();
    }

    @ApiOperation(httpMethod = "Put", value = "Método put para alterar um pedido por id")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Retorna uma mensagem de sucesso")})
    @PutMapping("/{id}")
    public ResponseEntity<Order> update(@PathVariable("id") Long id, @Valid @RequestBody Order order) {

        repository.update(id, order);

        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(httpMethod = "Delete", value = "Método delete para apagar um pedido por id")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Retorna uma mensagem de sucesso")})
    @DeleteMapping("/{id}")
    public ResponseEntity<Order> delete(@PathVariable("id") Long id) {

        final Order savedOrder = repository.getById(id);

        repository.delete(savedOrder);

        return new ResponseEntity(HttpStatus.OK);
    }

    private URI getUri(Order savedOrder) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedOrder.getId()).toUri();
    }

}
