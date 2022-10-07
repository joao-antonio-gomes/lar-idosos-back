package com.uninter.laridosos.dto.request;

import com.uninter.laridosos.model.entity.MedicineTreatment;
import com.uninter.laridosos.model.entity.Patient;
import com.uninter.laridosos.model.entity.Treatment;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MedicineTreatmentCreateDto implements Serializable {
    @NotNull(message = "Data de inicio do tratamento é obrigatório")
    private LocalDate beginDate;
    @NotNull(message = "Data fim do tratamento é obrigatório")
    private LocalDate endDate;
    @NotNull(message = "Doença é obrigatório")
    private String disease;
    @NotNull(message = "Paciente é obrigatório")
    private Long patientId;

    List<MedicineTreatmentDto> medicines;

    public Treatment toTreatmentEntity() {
        new Patient();
        return Treatment.builder()
                .patient(Patient.builder().id(patientId).build())
                .endDate(endDate)
                .beginDate(beginDate)
                .disease(disease)
                .build();
    }

    public List<MedicineTreatment> toMedicineTreatmentEntity(Treatment treatment) {
        if (medicines.isEmpty())
            return new ArrayList<>();

        return medicines.stream().map(medicine -> medicine.toMedicineTreatmentEntity(treatment)).collect(Collectors.toList());
    }
}
