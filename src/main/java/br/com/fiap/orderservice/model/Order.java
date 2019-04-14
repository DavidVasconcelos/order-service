package br.com.fiap.orderservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.math.BigDecimal;
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

    @ApiModelProperty(notes = "The database generated Order ID")
    private Long id;

    @ApiModelProperty( notes = "User name", required = true)
    private String fullName;

    @ApiModelProperty( notes = "User E-mail", required = true)
    private String email;

    @ApiModelProperty( notes = "The order delivery address", required = true)
    private String shippingAddress;

    @ApiModelProperty( notes = "The order itens", required = true)
    private List<Item> items = new ArrayList<>();

    private BigDecimal totalPrice;

    @ApiModelProperty( notes = "The order payment mode", required = true)
    private PaymentMode paymentMode;

    @JsonFormat(pattern = "MM/dd/yyyy HH:mm:ss.SSS",
            shape = JsonFormat.Shape.STRING,
            locale = "pt-BR",
            timezone = "Brazil/East")
    private Date orderDate;

    @ApiModelProperty( notes = "The order status", required = true)
    private Status status;

    @ApiModelProperty( notes = "The order transaction", required = true)
    private Transaction transaction;



}
