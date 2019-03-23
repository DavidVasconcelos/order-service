package br.com.fiap.orderservice.model;

import lombok.*;
import sun.util.locale.StringTokenIterator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private long id;

    private String email;

    private String firstName;

    private String lastName;

    private String address;

    private List<Item> items = new ArrayList<Item>();

    private Payment payment;

    private Date orderDate;

    private Status status;

    private Transaction transaction;

}
