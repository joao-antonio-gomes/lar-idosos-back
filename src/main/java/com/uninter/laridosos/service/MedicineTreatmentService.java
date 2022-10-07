package com.uninter.laridosos.service;

import com.uninter.laridosos.model.entity.MedicineTreatment;
import com.uninter.laridosos.model.repository.MedicineTreatmentRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@RequestScoped
public class MedicineTreatmentService {

    @Inject
    MedicineTreatmentRepository medicineTreatmentRepository;

    @Transactional
    public List<MedicineTreatment> create(List<MedicineTreatment> medicineTreatments) {
        return medicineTreatmentRepository.create(medicineTreatments);
    }

    public List<MedicineTreatment> getByMedicineId(Long medicineId) {
        return medicineTreatmentRepository.getByMedicineId(medicineId);
    }
}
