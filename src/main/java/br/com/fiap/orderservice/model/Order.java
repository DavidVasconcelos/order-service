package br.com.fiap.orderservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_EMPTY)
public class Order {

    private long id;

    private String email;

    private String fullName;

    private String address;

    private List<Item> items = new ArrayList<>();

    private Integer quantity;

    private Double totalPrice;

    private Payment payment;

    private Date orderDate;

    private Status status;

    private Transaction transaction;

}
