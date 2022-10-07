package com.uninter.laridosos.enumerator;

import com.uninter.laridosos.dto.response.DosageTypeResponseDto;
import com.uninter.laridosos.exception.BusinessException;
import lombok.Getter;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public enum DosageTypeEnum {
    PILULAS("Pilulas"),
    GRAMAS("Gramas"),
    MILILITROS("Mililitros"),
    MILIGRAMAS("Miligramas"),
    UNIDADES("Unidades"),
    CILINDROS("Cilindros"),
    CENTIMETROS("Centímetros");

    private String description;

    DosageTypeEnum(String description) {
        this.description = description;
    }

    public static List<DosageTypeResponseDto> getAllDosageType() {
        List<DosageTypeResponseDto> dosageList = new ArrayList<>();
        for (DosageTypeEnum dosage : DosageTypeEnum.values()) {
            dosageList.add(new DosageTypeResponseDto(dosage.ordinal(), dosage.getDescription()));
        }
        return dosageList;
    }

    public static DosageTypeEnum getByOrdinal(int dosage) {
        List<DosageTypeEnum> dosages = Arrays.stream(DosageTypeEnum.values()).filter(dosageTypeEnum -> dosageTypeEnum.ordinal() == dosage).collect(Collectors.toList());
        if (dosages.size() == 0)
            throw new BusinessException("Não foi encontrado tipo de dosagem com esse id", Response.Status.BAD_REQUEST);
        return dosages.get(0);
    }
}
