package com.uninter.laridosos.controller;

import com.uninter.laridosos.dto.request.MedicineTreatmentCreateDto;
import com.uninter.laridosos.dto.response.TreatmentGetResponseDto;
import com.uninter.laridosos.model.entity.MedicineTreatment;
import com.uninter.laridosos.model.entity.Treatment;
import com.uninter.laridosos.service.MedicineTreatmentService;
import com.uninter.laridosos.service.TreatmentService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("/treatments")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TreatmentController {

    @Inject
    TreatmentService treatmentService;
    @Inject
    MedicineTreatmentService medicineTreatmentService;

    @GET
    @Path("/{id}")
    public Response getAllByPatientId(@PathParam("id") Long patientId) {
        List<Treatment> treatmentList = treatmentService.getAllByPatientId(patientId);
        List<TreatmentGetResponseDto> treatmentDtoList = treatmentList.stream().map(TreatmentGetResponseDto::toResponseDto).collect(Collectors.toList());
        return Response.status(Response.Status.OK).entity(treatmentDtoList).build();
    }

    @POST
    public Response create(@Valid MedicineTreatmentCreateDto createDto) {
        Treatment treatment = treatmentService.create(createDto.toTreatmentEntity());
        List<MedicineTreatment> medicineTreatmentList = medicineTreatmentService.create(createDto.toMedicineTreatmentEntity(treatment));
        return Response.status(Response.Status.CREATED).entity(medicineTreatmentList).build();
    }
}
