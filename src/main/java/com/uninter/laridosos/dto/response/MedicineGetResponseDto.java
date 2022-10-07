package com.uninter.laridosos.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.uninter.laridosos.enumerator.DosageTypeEnum;
import com.uninter.laridosos.model.entity.Medicine;
import com.uninter.laridosos.model.entity.Patient;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

import static com.fasterxml.jackson.annotation.JsonInclude.Include;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class MedicineGetResponseDto implements Serializable {

    private Long id;
    private String name;
    private String description;
    private Integer concentration;

    public static MedicineGetResponseDto fromEntityToDto(Medicine medicine) {
        return MedicineGetResponseDto.builder()
                .id(medicine.getId())
                .name(medicine.getName())
                .description(medicine.getDescription())
                .concentration(medicine.getConcentration())
                .build();
    }
}
