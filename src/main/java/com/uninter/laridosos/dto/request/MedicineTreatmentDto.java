package com.uninter.laridosos.dto.request;

import com.uninter.laridosos.enumerator.DosageTypeEnum;
import com.uninter.laridosos.model.entity.Medicine;
import com.uninter.laridosos.model.entity.MedicineTreatment;
import com.uninter.laridosos.model.entity.Treatment;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MedicineTreatmentDto {
    @NotNull(message = "Dosagem é obrigatório")
    private Integer dosage;
    @NotNull(message = "Tipo dosagem é obrigatório")
    private Integer dosageType;
    @NotNull(message = "Intervalo é obrigatório")
    private Integer hourInterval;
    @NotNull(message = "Medicamento é obrigatório")
    private Long medicineId;

    public MedicineTreatment toMedicineTreatmentEntity(Treatment treatment) {
        return MedicineTreatment.builder()
                .medicine(Medicine.builder().id(medicineId).build())
                .dosage(dosage)
                .dosageType(DosageTypeEnum.getByOrdinal(dosageType))
                .hourInterval(hourInterval)
                .treatment(Treatment.builder().id(treatment.getId()).build())
                .build();
    }
}
