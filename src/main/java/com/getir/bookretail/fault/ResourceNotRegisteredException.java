package com.getir.bookretail.fault;

import lombok.Setter;

@Setter
public class ResourceNotRegisteredException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ResourceNotRegisteredException(String message) {
        super(message);
    }
}
