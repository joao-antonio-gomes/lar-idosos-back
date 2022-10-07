package com.uninter.laridosos.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.uninter.laridosos.model.entity.Medicine;
import lombok.*;

import java.io.Serializable;

import static com.fasterxml.jackson.annotation.JsonInclude.Include;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class MedicineGetAutocompleteResponseDto implements Serializable {

    private Long id;
    private String label;
    private String description;
    private Integer concentration;

    public static MedicineGetAutocompleteResponseDto fromEntityToDto(Medicine medicine) {
        return MedicineGetAutocompleteResponseDto.builder()
                .id(medicine.getId())
                .label(medicine.getName() + " - " + medicine.getConcentration() + "mg")
                .description(medicine.getDescription())
                .concentration(medicine.getConcentration())
                .build();
    }
}
