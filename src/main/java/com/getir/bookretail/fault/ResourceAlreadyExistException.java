package com.getir.bookretail.fault;

import lombok.Setter;

@Setter
public class ResourceAlreadyExistException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ResourceAlreadyExistException(String message) {
        super(message);
    }
}
