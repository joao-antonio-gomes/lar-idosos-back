package com.uninter.laridosos.model.repository;

import com.uninter.laridosos.model.entity.Patient;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class PatientRepository implements PanacheRepository<Patient> {
    public List<Patient> getAll() {
        return findAll().list();
    }

    public Patient findByCpf(String cpf) {
        return find("cpf", cpf).firstResult();
    }

    public Patient create(Patient patient) {
        persist(patient);
        return patient;
    }
}
