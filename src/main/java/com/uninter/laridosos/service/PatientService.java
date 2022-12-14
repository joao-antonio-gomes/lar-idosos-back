package com.uninter.laridosos.service;

import com.uninter.laridosos.dto.PaginationResult;
import com.uninter.laridosos.dto.filter.PatientGetAllFilter;
import com.uninter.laridosos.dto.response.PatientGetResponseDto;
import com.uninter.laridosos.exception.BusinessException;
import com.uninter.laridosos.model.entity.Patient;
import com.uninter.laridosos.model.repository.PatientRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
public class PatientService {

    @Inject
    PatientRepository patientRepository;

    public PaginationResult<PatientGetResponseDto> getAll(PatientGetAllFilter filter) {
        PaginationResult<PatientGetResponseDto> patientPaginationResult = new PaginationResult<>();
        List<Patient> patients = patientRepository.getAll(filter);
        patientPaginationResult.setCount(patientRepository.countWithFilter(filter));
        patientPaginationResult.setLimit(filter.getItemsPerPage());
        patientPaginationResult.setOffset(filter.getPage());

        List<PatientGetResponseDto> patientsResponse =
                patients.stream().map(PatientGetResponseDto::fromEntityToDto).collect(Collectors.toList());
        patientPaginationResult.setResult(patientsResponse);

        return patientPaginationResult;
    }

    @Transactional
    public Patient create(Patient patient) {
        patient.removeCpfMask();
        if (patientRepository.findByCpf(patient.getCpf()) != null) {
            throw new BusinessException("Paciente já cadastrado com CPF informado", Response.Status.BAD_REQUEST);
        }
        return patientRepository.create(patient);
    }

    public Patient getById(Long patientId) {
        Patient patient = patientRepository.findById(patientId);
        if (patient == null) {
            throw new BusinessException("Paciente com id " + patientId + " não encontrado", Response.Status.NOT_FOUND);
        }
        return patient;
    }

    @Transactional
    public Patient update(Patient patientRequest, Long patientId) {
        Patient patientDatabase = getById(patientId);
        patientDatabase = patientDatabase.update(patientRequest);
        patientDatabase.removeCpfMask();
        patientRepository.persistAndFlush(patientDatabase);
        return patientDatabase;
    }

    @Transactional
    public void delete(Long patientId) {
        patientRepository.deleteById(patientId);
    }
}
