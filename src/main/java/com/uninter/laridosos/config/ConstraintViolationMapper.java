package com.uninter.laridosos.config;

import com.uninter.laridosos.dto.response.ErrorResponseDto;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.Set;

@Provider
public class ConstraintViolationMapper implements ExceptionMapper<ConstraintViolationException> {
    @Override
    public Response toResponse(ConstraintViolationException constraintViolationException) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto();
        Set<ConstraintViolation<?>> constraintViolations = constraintViolationException.getConstraintViolations();
        StringBuilder erros = new StringBuilder();
        for (ConstraintViolation<?> violation : constraintViolations) {
            if (erros.toString().length() > 0) {
                erros.append(";");
            }
            erros.append(violation.getMessage());
        }
        errorResponseDto.setMessage(erros.toString());
        errorResponseDto.setStatus(400);
        return Response.status(400).entity(errorResponseDto).build();
    }
}
