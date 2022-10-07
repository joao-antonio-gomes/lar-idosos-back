package com.uninter.laridosos.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.uninter.laridosos.model.entity.Patient;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.fasterxml.jackson.annotation.JsonInclude.Include;

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
    private Integer gender;
    private boolean active;
    private List<TreatmentGetResponseDto> treatments;

    public static PatientGetResponseDto fromEntityToDto(Patient patient) {
        return PatientGetResponseDto.builder()
                .id(patient.getId())
                .name(patient.getName())
                .cpf(patient.getCpf())
                .birthDate(patient.getBirthDate())
                .phone(patient.getPhone())
                .gender(patient.getGender().ordinal())
                .active(patient.isActive())
                .treatments(new ArrayList<>(patient.getTreatments()).stream().map(TreatmentGetResponseDto::toResponseDto).sorted(Comparator.comparing(TreatmentGetResponseDto::getId)).collect(Collectors.toList()))
                .build();
    }
}
