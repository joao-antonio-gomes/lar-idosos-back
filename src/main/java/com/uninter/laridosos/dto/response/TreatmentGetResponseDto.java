package com.uninter.laridosos.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.uninter.laridosos.model.entity.Treatment;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static com.fasterxml.jackson.annotation.JsonInclude.Include;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class TreatmentGetResponseDto implements Serializable {

    private Long id;
    private LocalDate beginDate;
    private LocalDate endDate;
    private String disease;
    private List<MedicineTreatmentGetResponseDto> medsTreatment;

    public static TreatmentGetResponseDto toResponseDto(Treatment treatment) {
        return TreatmentGetResponseDto.builder()
                .id(treatment.getId())
                .beginDate(treatment.getBeginDate())
                .endDate(treatment.getEndDate())
                .disease(treatment.getDisease())
                .medsTreatment(treatment.getMedicinesTreatment().stream().map(MedicineTreatmentGetResponseDto::toResponseDto).collect(Collectors.toList()))
                .build();
    }
}
