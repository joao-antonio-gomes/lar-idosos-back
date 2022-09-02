package com.uninter.laridosos.controller;

import com.uninter.laridosos.dto.request.PatientCreateDto;
import com.uninter.laridosos.dto.request.PatientUpdateDto;
import com.uninter.laridosos.dto.response.PatientGetResponseDto;
import com.uninter.laridosos.model.entity.Patient;
import com.uninter.laridosos.service.PatientService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("/patient")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PatientController {

    @Inject
    PatientService patientService;

    @GET
    public Response getAll() {
        List<Patient> patientsEntity = patientService.getAll();
        List<PatientGetResponseDto> patientsResponse =
                patientsEntity.stream().map(PatientGetResponseDto::fromEntityToDto).collect(Collectors.toList());
        return Response.ok(patientsResponse).build();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long patientId) {
        Patient patient = patientService.getById(patientId);
        PatientGetResponseDto patientResponse = PatientGetResponseDto.fromEntityToDto(patient);
        return Response.ok(patientResponse).build();
    }

    @POST
    public Response create(@Valid PatientCreateDto patientCreateDto) {
        Patient patient = patientService.create(patientCreateDto.toEntity());
        return Response.status(Response.Status.CREATED).entity(PatientGetResponseDto.fromEntityToDto(patient)).build();
    }

    @PATCH
    @Path("/{id}")
    public Response update(@PathParam("id") Long patientId, @Valid PatientUpdateDto patientUpdateDto) {
        Patient patient = patientService.update(patientUpdateDto.toEntity(), patientId);
        return Response.status(Response.Status.OK).entity(PatientGetResponseDto.fromEntityToDto(patient)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long patientId) {
        patientService.delete(patientId);
        return Response.noContent().build();
    }
}
