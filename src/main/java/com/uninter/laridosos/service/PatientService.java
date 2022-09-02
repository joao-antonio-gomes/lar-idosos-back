package com.uninter.laridosos.service;

import com.uninter.laridosos.exception.BusinessException;
import com.uninter.laridosos.model.entity.Patient;
import com.uninter.laridosos.model.repository.PatientRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.util.List;

@RequestScoped
public class PatientService {

    @Inject
    PatientRepository patientRepository;

    public List<Patient> getAll() {
        return patientRepository.getAll();
    }

    @Transactional
    public Patient create(Patient patient) {
        if (patientRepository.findByCpf(patient.getCpf()) != null) {
            throw new BusinessException("Paciente já cadastrado com CPF informado", Response.Status.BAD_REQUEST);
        }
        patient.removeCpfMask();
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
