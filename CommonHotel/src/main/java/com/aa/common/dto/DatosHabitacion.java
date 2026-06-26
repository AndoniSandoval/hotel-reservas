package com.aa.common.dto;

import java.math.BigDecimal;

import com.aa.common.enums.TipoHabitacion;

public record DatosHabitacion(
		Long idHabitacion,
		Integer numeroHabitacion,
		TipoHabitacion tipoHabitacion,
	    BigDecimal precioNoche,
	    Integer capacidad
		) {}
