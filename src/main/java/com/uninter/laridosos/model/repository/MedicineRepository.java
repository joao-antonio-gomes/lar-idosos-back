package com.uninter.laridosos.model.repository;

import com.uninter.laridosos.dto.filter.MedicineGetAllFilter;
import com.uninter.laridosos.dto.filter.PatientGetAllFilter;
import com.uninter.laridosos.model.entity.Medicine;
import com.uninter.laridosos.model.entity.Patient;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class MedicineRepository implements PanacheRepository<Medicine> {
    public List<Medicine> getAll(MedicineGetAllFilter filter) {
        PanacheQuery<Medicine> query = findAll();
        if (filter.getName() != null) {
            query = find("lower(name) like concat('%', lower(?1), '%')", filter.getName());
        }
        return query.page(filter.getPage(), filter.getItemsPerPage()).list();
    }

    public Medicine create(Medicine patient) {
        persist(patient);
        return patient;
    }

    public Long countWithFilter(MedicineGetAllFilter filter) {
        PanacheQuery<Medicine> query = findAll();
        if (filter.getName() != null) {
            query = find("lower(name) like concat('%', lower(?1), '%')", filter.getName());
        }
        return query.count();
    }
}
