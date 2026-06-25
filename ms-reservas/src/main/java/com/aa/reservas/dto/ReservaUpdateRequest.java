package com.aa.reservas.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;

public record ReservaUpdateRequest(
		
		@NotNull(message = "La fecha de entrada es obligatoria")
	    LocalDateTime fechaEntrada,

	    @NotNull(message = "La fecha de salida es obligatoria")
	    LocalDateTime fechaSalida
	    
		) {}
