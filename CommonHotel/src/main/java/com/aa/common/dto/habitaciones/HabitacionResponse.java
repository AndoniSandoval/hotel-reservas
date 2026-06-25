package com.aa.common.dto.habitaciones;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.aa.common.enums.EstadoHabitacion;
import com.aa.common.enums.TipoHabitacion;

public record HabitacionResponse(
		Long idHabitacion,
		Integer numeroHabitacion,
		TipoHabitacion tipoHabitacion,
		BigDecimal precioNoche,
		Integer capacidad,
		EstadoHabitacion estadoHabitacion,
		LocalDateTime fechaCreacion,
		LocalDateTime fechaActualizacion) {
	

}
