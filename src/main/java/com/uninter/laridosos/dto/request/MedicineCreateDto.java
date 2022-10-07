package com.uninter.laridosos.dto.request;

import com.uninter.laridosos.enumerator.DosageTypeEnum;
import com.uninter.laridosos.model.entity.Medicine;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MedicineCreateDto implements Serializable {

    @NotNull(message = "Nome é obrigatório")
    private String name;
    @NotNull(message = "Descrição é obrigatório")
    private String description;
    @NotNull(message = "Concentração é obrigatório")
    private Integer concentration;

    public Medicine toEntity() {
        return Medicine.builder()
                .name(name)
                .description(description)
                .concentration(concentration)
                .build();
    }
}
