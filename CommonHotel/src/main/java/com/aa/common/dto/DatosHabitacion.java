package com.aa.common.dto;

import java.math.BigDecimal;

import com.aa.common.enums.TipoHabitacion;

public record DatosHabitacion(
		Integer numeroHabitacion,
		TipoHabitacion tipoHabitacion,
	    BigDecimal precioNoche,
	    Integer capacidad
		) {}
