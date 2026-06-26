package com.aa.reservas.mappers;

import org.springframework.stereotype.Component;

import com.aa.common.dto.DatosHabitacion;
import com.aa.common.dto.DatosHuesped;
import com.aa.common.dto.habitaciones.HabitacionResponse;
import com.aa.common.dto.huespedes.HuespedResponse;
import com.aa.common.mappers.CommonMapper;
import com.aa.reservas.dto.ReservaRequest;
import com.aa.reservas.dto.ReservaResponse;
import com.aa.reservas.entities.Reserva;
@Component
public class ReservaMapper implements CommonMapper<ReservaRequest, ReservaResponse, Reserva>{

	@Override
	public Reserva requestEntidad(ReservaRequest request) {
		// TODO Auto-generated method stub
		return Reserva.crear(request.idHuesped(), 
				request.idHabitacion(), 
				request.fechaEntrada(), 
				request.fechaSalida());
	}

	@Override
    public ReservaResponse entidadResponse(Reserva entidad) {
        throw new UnsupportedOperationException(
            "Use entidadResponse(Reserva, HuespedResponse, HabitacionResponse)");
    }
	
	// Método enriquecido — el que usará el service
    public ReservaResponse entidadResponse(Reserva entidad,
                                            HuespedResponse huesped,
                                            HabitacionResponse habitacion) {
        return new ReservaResponse(
            entidad.getIdReserva(),
            entidad.getFechaEntrada(),
            entidad.getFechaSalida(),
            entidad.getEstadoReserva(),
            entidad.getEstadoRegistro(),
            entidad.getFechaCreacion(),
            entidad.getFechaActualizacion(),
            new DatosHuesped(
                huesped.nombre() + " " + huesped.apellidoPaterno() + " " + huesped.apellidoMaterno(),
                huesped.email(),
                huesped.telefono(),
                huesped.documento(),
                huesped.nacionalidad()
            ),
            new DatosHabitacion(
                habitacion.numeroHabitacion(),
                habitacion.tipoHabitacion(),
                habitacion.precioNoche(),
                habitacion.capacidad()
            )
        );
    }

}
