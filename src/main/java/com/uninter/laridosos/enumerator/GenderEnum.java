package com.uninter.laridosos.enumerator;

import com.uninter.laridosos.dto.response.GenderResponseDto;
import com.uninter.laridosos.exception.BusinessException;
import lombok.Getter;

import javax.ws.rs.core.Response;
import java.util.*;
import java.util.stream.Collectors;

@Getter
public enum GenderEnum {
    MALE("Masculino"),
    FEMALE("Feminino");

    private String description;

    GenderEnum(String description) {
        this.description = description;
    }

    public static List<GenderResponseDto> getAllGenders() {
        List<GenderResponseDto> genders = new ArrayList<>();
        for (GenderEnum gender : GenderEnum.values()) {
            genders.add(new GenderResponseDto(gender.ordinal(), gender.getDescription()));
        }
        return genders;
    }

    public static GenderEnum getByOrdinal(int gender) {
        List<GenderEnum> genders = Arrays.stream(GenderEnum.values()).filter(genderEnum -> genderEnum.ordinal() == gender).collect(Collectors.toList());
        if (genders.size() == 0)
            throw new BusinessException("Não foi encontrado gênero com esse id", Response.Status.BAD_REQUEST);
        return genders.get(0);
    }
}
