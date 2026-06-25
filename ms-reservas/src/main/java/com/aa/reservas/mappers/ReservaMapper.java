package com.aa.reservas.mappers;

import org.springframework.stereotype.Component;

import com.aa.common.mappers.CommonMapper;
import com.aa.reservas.dto.ReservaRequest;
import com.aa.reservas.dto.ReservaResponse;
import com.aa.reservas.entities.Reserva;
@Component
public class ReservaMapper implements CommonMapper<ReservaRequest, ReservaResponse, Reserva>{

	@Override
	public Reserva requestEntidad(ReservaRequest request) {
		// TODO Auto-generated method stub
		return Reserva.crear(request.idHuesped(), request.idHabitacion(), request.fechaEntrada(), request.fechaSalida());
	}

	@Override
	public ReservaResponse entidadResponse(Reserva entidad) {
		// TODO Auto-generated method stub
		return new ReservaResponse(entidad.getIdReserva(), entidad.getIdHuesped(), entidad.getIdHabitacion(),
				entidad.getFechaEntrada(), entidad.getFechaSalida(), entidad.getEstadoReserva(), entidad.getEstadoRegistro(),
				entidad.getFechaCreacion(), entidad.getFechaActualizacion());
	}

}
