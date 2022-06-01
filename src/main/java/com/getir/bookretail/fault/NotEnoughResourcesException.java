package com.getir.bookretail.fault;

import lombok.Setter;

@Setter
public class NotEnoughResourcesException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public NotEnoughResourcesException(String message) {
        super(message);
    }
}
