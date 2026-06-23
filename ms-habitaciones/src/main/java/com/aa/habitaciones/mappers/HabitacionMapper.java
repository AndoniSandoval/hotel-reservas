package com.aa.habitaciones.mappers;

import com.aa.common.dto.habitaciones.HabitacionRequest;
import com.aa.common.dto.habitaciones.HabitacionResponse;
import com.aa.common.mappers.CommonMapper;
import com.aa.habitaciones.entities.Habitaciones;

public class HabitacionMapper implements CommonMapper<HabitacionRequest, HabitacionResponse, Habitaciones>{

	@Override
	public Habitaciones requestEntidad(HabitacionRequest request) {
		// TODO Auto-generated method stub
		return Habitaciones.crear(request.numeroHabitacion(), request.tipoHabitacion(), request.precioNoche(), request.capacidad());
	}

	@Override
	public HabitacionResponse entidadResponse(Habitaciones entidad) {
		// TODO Auto-generated method stub
		
		return new HabitacionResponse(entidad.getIdHabitacion(),
				entidad.getNumeroHabitacion(),
				entidad.getTipoHabitacion().toString(),
				entidad.getPrecioNoche(),
				entidad.getCapacidad(),
				entidad.getEstadoHabitacion().toString(),
				entidad.getFechaCreacion().toString(),
				entidad.getFechaActualizacion().toString());
	}

}
