package com.uninter.laridosos.model.repository;

import com.uninter.laridosos.dto.filter.PatientGetAllFilter;
import com.uninter.laridosos.model.entity.Patient;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class PatientRepository implements PanacheRepository<Patient> {
    public List<Patient> getAll(PatientGetAllFilter filter) {
        PanacheQuery<Patient> query = findAll();
        if (filter.getName() != null) {
            query = find("lower(name) like concat('%', lower(?1), '%')", filter.getName());
        }
        return query.page(filter.getPage(), filter.getItemsPerPage()).list();
    }

    public Patient findByCpf(String cpf) {
        return find("cpf", cpf).firstResult();
    }

    public Patient create(Patient patient) {
        persist(patient);
        return patient;
    }

    public Long countWithFilter(PatientGetAllFilter filter) {
        PanacheQuery<Patient> query = findAll();
        if (filter.getName() != null) {
            query = find("lower(name) like concat('%', lower(?1), '%')", filter.getName());
        }
        return query.count();
    }
}
