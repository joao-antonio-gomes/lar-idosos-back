package com.uninter.laridosos.service;

import com.uninter.laridosos.model.entity.Treatment;
import com.uninter.laridosos.model.repository.TreatmentRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@RequestScoped
public class TreatmentService {

    @Inject
    TreatmentRepository treatmentRepository;

    @Transactional
    public Treatment create(Treatment treatment) {
        return treatmentRepository.create(treatment);
    }

    public List<Treatment> getAllByPatientId(Long patientId) {
        return treatmentRepository.getAllByPatientId(patientId);
    }
}
