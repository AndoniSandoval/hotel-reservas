package com.aa.reservas.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aa.common.enums.EstadoRegistro;
import com.aa.common.exceptions.RecursoNoEncontradoException;
import com.aa.reservas.dto.ReservaRequest;
import com.aa.reservas.dto.ReservaResponse;
import com.aa.reservas.entities.Reserva;
import com.aa.reservas.mappers.ReservaMapper;
import com.aa.reservas.repositories.ReservaRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class ReservaServiceImpl implements ReservaService{
	private final ReservaRepository reservaRepository;
	private final ReservaMapper reservaMapper;
	
	@Override
	public List<ReservaResponse> listar() {
		// TODO Auto-generated method stub
		return reservaRepository.findByEstadoRegistro(EstadoRegistro.ACTIVO).stream().map(reservaMapper::entidadResponse).toList();
	}

	@Override
	public ReservaResponse obtenerPorId(Long id) {
		// TODO Auto-generated method stub
		
		return reservaMapper.entidadResponse(obtenerReservaActivaPorIdOException(id));
	}

	@Override
	public ReservaResponse registrar(ReservaRequest request) {
		// TODO Auto-generated method stub
		Reserva reserva = reservaMapper.requestEntidad(request);
		
		reservaRepository.save(reserva);
		return reservaMapper.entidadResponse(reserva);
	}

	@Override
	public ReservaResponse actualizar(ReservaRequest request, Long id) {
		// TODO Auto-generated method stub
		Reserva reserva = obtenerReservaActivaPorIdOException(id);
		
		reserva.actualizar(request.fechaEntrada(), request.fechaSalida());
		return null;
	}

	@Override
	public void eliminar(Long id) {
		// TODO Auto-generated method stub
		Reserva reserva = obtenerReservaActivaPorIdOException(id);
		reserva.eliminar();
		
		
	}
	
	
	private Reserva obtenerReservaActivaPorIdOException(Long id) {
		return reservaRepository.findByIdReservaAndEstadoRegistro(id,EstadoRegistro.ACTIVO).orElseThrow(()-> new RecursoNoEncontradoException("No se encontro la reserva con id "+id));
	}
	
	
	
}
