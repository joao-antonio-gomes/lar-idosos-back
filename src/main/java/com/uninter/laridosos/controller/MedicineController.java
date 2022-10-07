package com.uninter.laridosos.controller;

import com.uninter.laridosos.dto.PaginationResult;
import com.uninter.laridosos.dto.filter.MedicineGetAllFilter;
import com.uninter.laridosos.dto.request.MedicineCreateDto;
import com.uninter.laridosos.dto.request.MedicineUpdateDto;
import com.uninter.laridosos.dto.request.PatientCreateDto;
import com.uninter.laridosos.dto.request.PatientUpdateDto;
import com.uninter.laridosos.dto.response.MedicineGetAutocompleteResponseDto;
import com.uninter.laridosos.dto.response.MedicineGetResponseDto;
import com.uninter.laridosos.dto.response.PatientGetResponseDto;
import com.uninter.laridosos.enumerator.DosageTypeEnum;
import com.uninter.laridosos.model.entity.Medicine;
import com.uninter.laridosos.model.entity.Patient;
import com.uninter.laridosos.service.MedicineService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("/medicines")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MedicineController {

    @Inject
    MedicineService medicineService;

    @GET
    public Response getAll(@BeanParam MedicineGetAllFilter filter) {
        PaginationResult<MedicineGetResponseDto> medResultDto = new PaginationResult<>();
        PaginationResult<Medicine> medResultEntity = medicineService.getAll(filter);
        List<Medicine> medEntity = medResultEntity.getResult();
        List<MedicineGetResponseDto> medDto =
                medEntity.stream().map(MedicineGetResponseDto::fromEntityToDto).collect(Collectors.toList());
        medResultDto.setResult(medDto);
        return Response.ok(medResultDto).build();
    }

    @GET
    @Path("/autocomplete")
    public Response getAllAutocomplete(@BeanParam MedicineGetAllFilter filter) {
        PaginationResult<MedicineGetAutocompleteResponseDto> medResultDto = new PaginationResult<>();
        PaginationResult<Medicine> medResultEntity = medicineService.getAll(filter);
        List<Medicine> medEntity = medResultEntity.getResult();
        List<MedicineGetAutocompleteResponseDto> medDto =
                medEntity.stream().map(MedicineGetAutocompleteResponseDto::fromEntityToDto).collect(Collectors.toList());
        medResultDto.setResult(medDto);
        return Response.ok(medResultDto).build();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long medicineId) {
        Medicine patient = medicineService.getById(medicineId);
        MedicineGetResponseDto patientResponse = MedicineGetResponseDto.fromEntityToDto(patient);
        return Response.ok(patientResponse).build();
    }

    @GET
    @Path("/dosage-type")
    public Response getDosageTypes() {
        return Response.ok(DosageTypeEnum.getAllDosageType()).build();
    }

    @POST
    public Response create(@Valid MedicineCreateDto medicineCreateDto) {
        Medicine medicine = medicineService.create(medicineCreateDto.toEntity());
        return Response.status(Response.Status.CREATED).entity(MedicineGetResponseDto.fromEntityToDto(medicine)).build();
    }

    @PATCH
    @Path("/{id}")
    public Response update(@PathParam("id") Long medicineId, @Valid MedicineUpdateDto medicineUpdateDtoe) {
        Medicine medicine = medicineService.update(medicineUpdateDtoe.toEntity(), medicineId);
        return Response.status(Response.Status.OK).entity(MedicineGetResponseDto.fromEntityToDto(medicine)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long medicineId) {
        medicineService.delete(medicineId);
        return Response.noContent().build();
    }
}
