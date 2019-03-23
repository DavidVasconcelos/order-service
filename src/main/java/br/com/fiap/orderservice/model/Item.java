package br.com.fiap.orderservice.model;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    private String description;

    private Double price;
}
