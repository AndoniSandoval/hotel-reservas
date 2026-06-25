package com.aa.reservas.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ReservaRequest(
		
		@NotNull(message = "El id del huesped es obligatorio")
	    @Positive(message = "El id del huesped debe ser positivo")
	    Long idHuesped,

	    @NotNull(message = "El id de la habitacion es obligatorio")
	    @Positive(message = "El id de la habitacion debe ser positivo")
	    Long idHabitacion,

	    @NotNull(message = "La fecha de entrada es obligatoria")
	    @FutureOrPresent(message = "La fecha de entrada no puede ser en el pasado")
	    LocalDateTime fechaEntrada,

	    @NotNull(message = "La fecha de salida es obligatoria")
	    @Future(message = "La fecha de salida debe ser una fecha futura")
	    LocalDateTime fechaSalida
	    
	    ) {}
