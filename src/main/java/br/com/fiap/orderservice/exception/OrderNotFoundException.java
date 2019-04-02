package br.com.fiap.orderservice.exception;

public class OrderNotFoundException extends RuntimeException {

	public OrderNotFoundException(final String message) {
		super(message);
	}

}