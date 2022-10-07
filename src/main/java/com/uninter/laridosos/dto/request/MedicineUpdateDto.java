package com.uninter.laridosos.dto.request;

import com.uninter.laridosos.model.entity.Medicine;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MedicineUpdateDto implements Serializable {

    private String name;
    private String description;
    private Integer concentration;

    public Medicine toEntity() {
        return Medicine.builder()
                .name(name)
                .description(description)
                .concentration(concentration)
                .build();
    }
}
