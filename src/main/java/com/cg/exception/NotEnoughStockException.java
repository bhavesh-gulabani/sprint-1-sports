package com.cg.exception;

public class NotEnoughStockException extends Exception {

	private static final long serialVersionUID = 1L;

	public NotEnoughStockException(String message) {
		super(message);
	}
}
