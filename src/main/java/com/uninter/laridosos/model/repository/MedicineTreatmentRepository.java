package com.uninter.laridosos.model.repository;

import com.uninter.laridosos.model.entity.MedicineTreatment;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class MedicineTreatmentRepository implements PanacheRepository<MedicineTreatment> {
    public List<MedicineTreatment> create(List<MedicineTreatment> medicineTreatments) {
        persist(medicineTreatments);
        return medicineTreatments;
    }

    public List<MedicineTreatment> getByMedicineId(Long medicineId) {
        return find("medicine.id", medicineId).list();
    }
}
