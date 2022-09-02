package com.uninter.laridosos.dto.request;

import com.uninter.laridosos.constraint.Cpf;
import com.uninter.laridosos.model.entity.Patient;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatientUpdateDto implements Serializable {

    private String name;
    @Cpf
    private String cpf;
    private LocalDate birthDate;
    private String phone;

    public Patient toEntity() {
        return Patient.builder()
                .name(name)
                .cpf(cpf)
                .birthDate(birthDate)
                .phone(phone)
                .build();
    }
}
