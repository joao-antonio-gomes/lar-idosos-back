package com.uninter.laridosos.config;

import com.uninter.laridosos.dto.response.ErrorResponseDto;
import com.uninter.laridosos.exception.BusinessException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class BusinessExceptionMapper implements ExceptionMapper<BusinessException> {
    @Override
    public Response toResponse(BusinessException e) {
        ErrorResponseDto error = new ErrorResponseDto();
        int status = e.getResponse().getStatus();
        error.setMessage(e.getMessage());
        error.setStatus(status);
        return Response.status(status).entity(error).build();
    }
}
