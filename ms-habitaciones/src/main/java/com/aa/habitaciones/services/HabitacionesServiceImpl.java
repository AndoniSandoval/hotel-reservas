package com.aa.habitaciones.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aa.common.dto.habitaciones.HabitacionRequest;
import com.aa.common.dto.habitaciones.HabitacionResponse;
import com.aa.common.enums.EstadoHabitacion;
import com.aa.common.enums.EstadoRegistro;
import com.aa.common.exceptions.RecursoNoEncontradoException;
import com.aa.habitaciones.entities.Habitaciones;
import com.aa.habitaciones.mappers.HabitacionMapper;
import com.aa.habitaciones.repositories.HabitacionesRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Transactional
@Slf4j

public class HabitacionesServiceImpl implements HabitacionesService{
	private final HabitacionesRepository habitacionesRepository;
	private final HabitacionMapper habitacionMapper;
	
	@Override
	@Transactional(readOnly = true)
	public List<HabitacionResponse> listar() {
		// TODO Auto-generated method stub
		return habitacionesRepository.findByEstadoRegistro(EstadoRegistro.ACTIVO).stream().map(habitacionMapper::entidadResponse).toList();
	}

	@Override
	@Transactional(readOnly = true)
	public HabitacionResponse obtenerPorId(Long id) {
		// TODO Auto-generated method stub
		
		return habitacionMapper.entidadResponse(obtenerHabitacionActivaPorIdOException(id));
	}

	@Override
	public HabitacionResponse registrar(HabitacionRequest request) {
		// TODO Auto-generated method stub
		
		if(habitacionesRepository.existsByNumeroHabitacionAndEstadoRegistro(request.numeroHabitacion(),EstadoRegistro.ACTIVO))
			throw new IllegalArgumentException("Ya existe una habitacion con el numero: "+request.numeroHabitacion());
		
		Habitaciones habitacion = habitacionMapper.requestEntidad(request);
		
		habitacionesRepository.save(habitacion);
		
		return habitacionMapper.entidadResponse(habitacion);
	}

	@Override
	public HabitacionResponse actualizar(HabitacionRequest request, Long id) {
		// TODO Auto-generated method stub
		
		Habitaciones habitacion = obtenerHabitacionActivaPorIdOException(id);
		
		if(habitacionesRepository.existsByNumeroHabitacionAndIdHabitacionNotAndEstadoRegistro(request.numeroHabitacion(),id,EstadoRegistro.ACTIVO))
			throw new IllegalArgumentException("Ya existe una habitacion con el numero: "+request.numeroHabitacion());
		
		habitacion.actualizar(request.numeroHabitacion(), request.tipoHabitacion(),
				request.precioNoche(), request.capacidad());
		return habitacionMapper.entidadResponse(habitacion);
	}

	@Override
	public void eliminar(Long id) {
		// TODO Auto-generated method stub
		Habitaciones habitacion = obtenerHabitacionActivaPorIdOException(id);
		habitacion.eliminar();
	}
	
	
	private Habitaciones obtenerHabitacionActivaPorIdOException(Long id) {
		return habitacionesRepository.findByIdHabitacionAndEstadoRegistro(id,EstadoRegistro.ACTIVO).orElseThrow(()-> new RecursoNoEncontradoException("No se encontro la habitacion"));
	}
	
	@Override
	public void cambiarEstado(Long idHabitacion, Long idEstado) {

	    Habitaciones habitacion = obtenerHabitacionActivaPorIdOException(idHabitacion);

	    EstadoHabitacion nuevoEstado =
	        EstadoHabitacion.obtenerEstadoHabitacionPorCodigo(idEstado);

	    habitacion.cambiarEstado(nuevoEstado);
	}
}
