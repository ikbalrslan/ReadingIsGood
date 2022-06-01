package com.getir.bookretail.fault;

import lombok.Setter;

@Setter
public class ResourceNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
