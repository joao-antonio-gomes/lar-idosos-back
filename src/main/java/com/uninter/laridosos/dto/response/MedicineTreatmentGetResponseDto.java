package com.uninter.laridosos.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.uninter.laridosos.enumerator.DosageTypeEnum;
import com.uninter.laridosos.model.entity.MedicineTreatment;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

import static com.fasterxml.jackson.annotation.JsonInclude.Include;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class MedicineTreatmentGetResponseDto implements Serializable {

    private Long id;
    private MedicineGetResponseDto medicine;
    private Integer dosage;
    private String dosageType;
    private Integer hourInterval;

    public static MedicineTreatmentGetResponseDto toResponseDto(MedicineTreatment medicineTreatment) {
        return MedicineTreatmentGetResponseDto.builder()
                .id(medicineTreatment.getId())
                .dosage(medicineTreatment.getDosage())
                .dosageType(medicineTreatment.getDosageType().getDescription())
                .hourInterval(medicineTreatment.getHourInterval())
                .medicine(MedicineGetResponseDto.fromEntityToDto(medicineTreatment.getMedicine()))
                .build();
    }
}
