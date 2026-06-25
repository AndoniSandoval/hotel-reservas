package com.aa.reservas.dto;

import java.time.LocalDateTime;

import com.aa.common.enums.EstadoRegistro;
import com.aa.common.enums.EstadoReserva;

public record ReservaResponse(
		Long idReserva,
	    Long idHuesped,
	    Long idHabitacion,
	    LocalDateTime fechaEntrada,
	    LocalDateTime fechaSalida,
	    EstadoReserva estadoReserva,
	    EstadoRegistro estadoRegistro,
	    LocalDateTime fechaCreacion,
	    LocalDateTime fechaActualizacion
	 
		) {}
