package com.uninter.laridosos.model.repository;

import com.uninter.laridosos.model.entity.Treatment;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class TreatmentRepository implements PanacheRepository<Treatment> {
    public Treatment create(Treatment treatment) {
        persist(treatment);
        return treatment;
    }

    public List<Treatment> getAllByPatientId(Long patientId) {
        return find("patient.id", patientId).list();
    }
}
