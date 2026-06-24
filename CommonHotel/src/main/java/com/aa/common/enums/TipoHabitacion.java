package com.aa.common.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TipoHabitacion {
	SENCILLA,
	DOBLE,
	SUITE;
	
	@JsonCreator
    public static TipoHabitacion fromString(String value) {
        return switch (value.toUpperCase()) {
            case "SENCILLA" -> SENCILLA;
            case "DOBLE" -> DOBLE;
            case "SUITE" -> SUITE;
            default -> throw new IllegalArgumentException("Tipo de habitación inválido");
        };
	}
}
