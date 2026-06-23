package com.aa.common.dto.habitaciones;

import com.aa.common.enums.TipoHabitacion;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record HabitacionRequest(
		@NotNull(message = "El numero de habitacion es requerido")
		@Min(value = 1, message = "El numero de habitacion minimo es 1")
		Integer numeroHabitacion,
		@NotBlank(message = "El tipo de habitacion es requerido")
		TipoHabitacion tipoHabitacion, 
		@NotNull(message = "El precio por noche es requerido")
		@DecimalMin(value = "0.0" ,message = "El precio debe ser mayor a 0")
		Double precioNoche,
		@NotNull(message = "La capacidad es requerida")
		@Min(value = 1, message = "La capaciada minima es 1")
		Integer capacidad) {
}
