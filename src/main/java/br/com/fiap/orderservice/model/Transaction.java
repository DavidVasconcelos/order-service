package br.com.fiap.orderservice.model;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    private Long id;

    private Integer cardNumber;

    private String expiration;

    private Brand brand;


}
