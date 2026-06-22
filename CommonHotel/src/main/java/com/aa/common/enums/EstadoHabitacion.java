package com.aa.common.enums;

import com.aa.common.exceptions.RecursoNoEncontradoException;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EstadoHabitacion {

	DISPONIBLE(1L,"Lista para asignarse"),
	OCUPADA(2L,"Asignada a una reserva"),
	LIMPIEZA(3L,"En limpieza"),
	MANTENIMIENTO(4L,"En reparación");
	
	private final Long codigo;
	
	private final String descipcion;
	
	public static EstadoHabitacion obtenerEstadoHabitacionPorCodigo(Long codigo) {


        for (EstadoHabitacion d : values()) {

            if (d.codigo == codigo) {

                return d;
            }
        }

        throw new RecursoNoEncontradoException(
                "No existe el estado de habitacion con el codigo: "
                        + codigo
        );
    }
}
