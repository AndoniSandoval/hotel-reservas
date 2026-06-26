package com.aa.reservas.dto;

import java.time.LocalDateTime;

import com.aa.common.dto.DatosHabitacion;
import com.aa.common.dto.DatosHuesped;
import com.aa.common.enums.EstadoRegistro;
import com.aa.common.enums.EstadoReserva;

public record ReservaResponse(
		Long idReserva,
	    LocalDateTime fechaEntrada,
	    LocalDateTime fechaSalida,
	    EstadoReserva estadoReserva,
	    EstadoRegistro estadoRegistro,
	    LocalDateTime fechaCreacion,
	    LocalDateTime fechaActualizacion,
	    DatosHuesped huesped,
	    DatosHabitacion habitacion
	 
		) {}
