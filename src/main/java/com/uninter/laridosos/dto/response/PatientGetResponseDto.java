package com.uninter.laridosos.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.uninter.laridosos.model.entity.Patient;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

import static com.fasterxml.jackson.annotation.JsonInclude.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class PatientGetResponseDto implements Serializable {

    private Long id;
    private String name;
    private String cpf;
    private LocalDate birthDate;
    private String phone;
    private String gender;
    private boolean active;

    public static PatientGetResponseDto fromEntityToDto(Patient patient) {
        return PatientGetResponseDto.builder()
                .id(patient.getId())
                .name(patient.getName())
                .cpf(patient.getCpf())
                .birthDate(patient.getBirthDate())
                .phone(patient.getPhone())
                .gender(patient.getGender().getDescription())
                .active(patient.isActive())
                .build();
    }
}
