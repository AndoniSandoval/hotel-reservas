package com.aa.common.dto;

public record DatosHuesped(
		Long idHuesped,
		String nombre,
	    String email,
	    String telefono,
	    String documento,
	    String nacionalidad
		) {}
