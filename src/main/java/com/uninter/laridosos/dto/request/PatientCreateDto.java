package com.uninter.laridosos.dto.request;

import com.uninter.laridosos.constraint.Cpf;
import com.uninter.laridosos.enumerator.GenderEnum;
import com.uninter.laridosos.model.entity.Patient;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatientCreateDto implements Serializable {

    @NotNull(message = "Nome é obrigatório")
    private String name;
    @NotNull(message = "CPF é obrigatório")
    @Cpf
    private String cpf;
    @NotNull(message = "Data de nascimento é obrigatório")
    private LocalDate birthDate;
    private String phone;
    @NotNull(message = "Gênero é obrigatório")
    private int gender;

    public Patient toEntity() {
        return Patient.builder()
                .name(name)
                .cpf(cpf)
                .birthDate(birthDate)
                .phone(phone)
                .gender(GenderEnum.getByOrdinal(gender))
                .build();
    }
}
