package com.uninter.laridosos.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.io.Serializable;

public class BusinessException extends WebApplicationException implements Serializable {
    public BusinessException() {
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Response.Status status) {
        super(message, status);
    }
}
