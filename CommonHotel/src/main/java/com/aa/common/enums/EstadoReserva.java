package com.aa.common.enums;

import com.aa.common.exceptions.RecursoNoEncontradoException;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EstadoReserva {
	CONFIRMADA(1L,"Reserva creada"),
	EN_CURSO(2L,"Check-in realizado"),
	FINALIZADA(3L,"Check-out realizado"),
	CANCELADA(4L,"Reserva cancelada");
private final Long codigo;
	
	private final String descipcion;
	
	public static EstadoReserva obtenerEstadoReservaPorCodigo(Long codigo) {


        for (EstadoReserva d : values()) {

            if (d.codigo == codigo) {

                return d;
            }
        }

        throw new RecursoNoEncontradoException(
                "No existe el estado de reserva con el codigo: "
                        + codigo
        );
    }
}
