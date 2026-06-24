package com.aa.common.dto.habitaciones;

import java.math.BigDecimal;

public record HabitacionResponse(
		Long idHabitacion,
		Integer numeroHabitacion,
		String tipoHabitacion,
		BigDecimal precioNoche,
		Integer capacidad,
		String estadoHabitacion,
		String fechaCreacion,
		String fechaActualizacion) {
	

}
