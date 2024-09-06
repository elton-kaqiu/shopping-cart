package com.totty.shoppingcart.exceptions;

public class ImageNotFoundException extends RuntimeException {
    public ImageNotFoundException() {
    }

    public ImageNotFoundException(String message) {
        super(message);
    }
}
