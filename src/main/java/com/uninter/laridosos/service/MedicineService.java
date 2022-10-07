package com.uninter.laridosos.service;

import com.uninter.laridosos.dto.PaginationResult;
import com.uninter.laridosos.dto.filter.MedicineGetAllFilter;
import com.uninter.laridosos.dto.response.MedicineGetResponseDto;
import com.uninter.laridosos.dto.response.PatientGetResponseDto;
import com.uninter.laridosos.exception.BusinessException;
import com.uninter.laridosos.model.entity.Medicine;
import com.uninter.laridosos.model.entity.MedicineTreatment;
import com.uninter.laridosos.model.repository.MedicineRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
public class MedicineService {

    @Inject
    MedicineRepository medicineRepository;
    @Inject
    MedicineTreatmentService medicineTreatmentService;

    public PaginationResult<Medicine> getAll(MedicineGetAllFilter filter) {
        PaginationResult<Medicine> medicinePaginationResult = new PaginationResult<>();
        List<Medicine> medicines = medicineRepository.getAll(filter);
        medicinePaginationResult.setCount(medicineRepository.countWithFilter(filter));
        medicinePaginationResult.setLimit(filter.getItemsPerPage());
        medicinePaginationResult.setOffset(filter.getPage());

        List<MedicineGetResponseDto> medResponse =
                medicines.stream().map(MedicineGetResponseDto::fromEntityToDto).collect(Collectors.toList());
        medicinePaginationResult.setResult(medicines);

        return medicinePaginationResult;
    }

    @Transactional
    public Medicine create(Medicine medicine) {
        return medicineRepository.create(medicine);
    }

    public Medicine getById(Long medId) {
        Medicine medicine = medicineRepository.findById(medId);
        if (medicine == null) {
            throw new BusinessException("Remédio com id " + medId + " não encontrado", Response.Status.NOT_FOUND);
        }
        return medicine;
    }

    @Transactional
    public Medicine update(Medicine medRequest, Long medId) {
        Medicine medDatabase = getById(medId);
        medDatabase = medDatabase.update(medRequest);
        medicineRepository.persistAndFlush(medDatabase);
        return medDatabase;
    }

    @Transactional
    public void delete(Long medicineId) {
        List<MedicineTreatment> treatments = medicineTreatmentService.getByMedicineId(medicineId);
        if (!treatments.isEmpty())
            throw new BusinessException("Não é possível excluir o medicamento pois ele já é utilizado em um ou mais tratamentos!");
        medicineRepository.deleteById(medicineId);
    }
}
