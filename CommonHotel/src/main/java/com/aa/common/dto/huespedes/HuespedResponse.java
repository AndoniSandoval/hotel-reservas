package com.aa.common.dto.huespedes;

import java.time.LocalDate;

import com.aa.common.enums.EstadoRegistro;

public record HuespedResponse(
		 Long idHuesped,
		 String nombre,
		 String apellidoPaterno,
		 String apellidoMaterno,
		 String email,
		 String telefono,
		 String documento,
		 String nacionalidad,
		 EstadoRegistro estadoRegistro,
		 LocalDate fechaCreacion,
		 LocalDate fechaActualizacion
		) {}
