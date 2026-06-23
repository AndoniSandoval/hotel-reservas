package com.aa.common.dto.habitaciones;

public record HabitacionResponse(
		Long idHabitacion,
		Integer numeroHabitacion,
		String tipoHabitacion,
		Double precioNoche,
		Integer capacidad,
		String estadoHabitacion,
		String fechaCreacion,
		String fechaActualizacion) {
	

}
