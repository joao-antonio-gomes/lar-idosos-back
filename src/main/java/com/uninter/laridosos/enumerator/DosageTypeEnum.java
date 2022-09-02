package com.uninter.laridosos.enumerator;

import lombok.Getter;

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
}
