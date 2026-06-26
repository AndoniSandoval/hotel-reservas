package com.aa.reservas.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aa.common.dto.habitaciones.HabitacionResponse;
import com.aa.common.dto.huespedes.HuespedResponse;
import com.aa.common.enums.EstadoHabitacion;
import com.aa.common.enums.EstadoRegistro;
import com.aa.common.enums.EstadoReserva;
import com.aa.common.exceptions.EntidadRelacionadaException;
import com.aa.common.exceptions.RecursoNoEncontradoException;
import com.aa.reservas.clients.HabitacionClient;
import com.aa.reservas.clients.HuespedClient;
import com.aa.reservas.dto.ReservaRequest;
import com.aa.reservas.dto.ReservaResponse;
import com.aa.reservas.dto.ReservaUpdateRequest;
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
	private final HuespedClient huespedClient;
    private final HabitacionClient habitacionClient;
	
	@Override
	public List<ReservaResponse> listar() {
		log.info("Listando todas las reservas activas");
		return reservaRepository.findByEstadoRegistro(EstadoRegistro.ACTIVO)
				.stream()
				.map(this::enriquecer)
				.toList();
	}

	@Override
	public ReservaResponse obtenerPorId(Long id) {
		log.info("Buscando reserva activa con id: {}", id);
		return enriquecer(obtenerReservaActivaPorIdOException(id));
	}

	@Override
	public ReservaResponse registrar(ReservaRequest request) {
		log.info("Registrando nueva reserva para huesped id: {} habitacion id: {}",
                request.idHuesped(), request.idHabitacion());
		
		HabitacionResponse habitacion = 
			    habitacionClient.obtenerHabitacion(request.idHabitacion());
		
		if (habitacion.estadoHabitacion() != EstadoHabitacion.DISPONIBLE) {
		    throw new EntidadRelacionadaException(
		        "La habitacion con id " + request.idHabitacion() + " no esta disponible");
		}
		
		// verifica que huesped existe y esta activo
		log.info("Verificando huesped activo con id: {}", request.idHuesped());
        	huespedClient.obtenerHuespedActivo(request.idHuesped());
        
        // verifica que la habitacion existe, está activa y disponible
        log.info("Verificando habitacion disponible con id: {}", request.idHabitacion());
        	habitacionClient.obtenerHabitacion(request.idHabitacion());
        
		Reserva reserva = reservaMapper.requestEntidad(request);
		reservaRepository.save(reserva);
		
		//cambiar habitacion a ocupada
        log.info("Cambiando habitacion id: {} a OCUPADA", request.idHabitacion());
        	habitacionClient.cambiarEstadoHabitacion(request.idHabitacion(), 2L); // 2 = OCUPADA

        log.info("Reserva registrada exitosamente con id: {}", reserva.getIdReserva());
     
		return reservaMapper.entidadResponse(reserva);
	}

	@Override
	public ReservaResponse actualizar(ReservaRequest request, Long id) {
//		Reserva reserva = obtenerReservaActivaPorIdOException(id);
//		
//		reserva.actualizar(request.fechaEntrada(), request.fechaSalida());
//		return null;
		throw new UnsupportedOperationException(
		        "Use actualizarFechas() para modificar una reserva");
	}

	@Override
	public void eliminar(Long id) {
		log.info("Eliminando reserva con id: {}", id);
		Reserva reserva = obtenerReservaActivaPorIdOException(id);
		
		//validar que no este en_curso
		if (reserva.getEstadoReserva() == EstadoReserva.EN_CURSO) {
	        throw new EntidadRelacionadaException(
	            "No se puede eliminar una reserva en estado EN_CURSO");
	    }
		
		reserva.eliminar();
		
		log.info("Reserva eliminada logicamente con id: {}", id);
		
	}
	
//	private Reserva obtenerReservaActivaPorIdOException(Long id) {
//		return reservaRepository.findByIdReservaAndEstadoRegistro(id,EstadoRegistro.ACTIVO).orElseThrow(()-> new RecursoNoEncontradoException("No se encontro la reserva con id "+id));
//	}

	@Override
    @Transactional(readOnly = true)
    public boolean huespedTieneReservasEnCurso(Long idHuesped) {
        log.info("Verificando si huesped id: {} tiene reservas EN_CURSO", idHuesped);
        return reservaRepository.existsByIdHuespedAndEstadoReserva(
                idHuesped, EstadoReserva.EN_CURSO);
    }
	
	@Override
    @Transactional(readOnly = true)
    public boolean habitacionTieneReservasEnCurso(Long idHabitacion) {
        log.info("Verificando si habitacion id: {} tiene reservas EN_CURSO", idHabitacion);
        return reservaRepository.existsByIdHabitacionAndEstadoReserva(
                idHabitacion, EstadoReserva.EN_CURSO);
    }
	
	@Override
	public ReservaResponse actualizarFechas(ReservaUpdateRequest request, Long id) {
	    log.info("Actualizando fechas de reserva con id: {}", id);
	    Reserva reserva = obtenerReservaActivaPorIdOException(id);

	    switch (reserva.getEstadoReserva()) {
	        case CONFIRMADA -> {
	            log.info("Reserva CONFIRMADA — actualizando fechaEntrada y fechaSalida");
	            reserva.actualizar(request.fechaEntrada(), request.fechaSalida());
	        }
	        case EN_CURSO -> {
	            log.info("Reserva EN_CURSO — actualizando solo fechaSalida");
	            reserva.actualizarFechaSalida(request.fechaSalida());
	        }
	        default -> throw new IllegalStateException(
	                "No se pueden modificar fechas de una reserva en estado: "
	                + reserva.getEstadoReserva());
	    }

	    log.info("Fechas de reserva id: {} actualizadas exitosamente", id);
	    return enriquecer(reserva);
	}
	
	//  METODOS PRIVADOS HELPER
    private Reserva obtenerReservaActivaPorIdOException(Long id) {
        log.info("Buscando reserva con estado {} con id: {}", EstadoRegistro.ACTIVO, id);
        return reservaRepository.findByIdReservaAndEstadoRegistro(id, EstadoRegistro.ACTIVO)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "No se encontro la reserva activa con id: " + id));
    }

    private void validarTransicionEstado(EstadoReserva estadoActual,
                                          EstadoReserva nuevoEstado,
                                          Long idHabitacion) {
        switch (estadoActual) {
            case CONFIRMADA -> {
                if (nuevoEstado == EstadoReserva.EN_CURSO) {
                    // Check-in — habitacion permanece ocupada
                    log.info("Check-in: habitacion id: {} permanece OCUPADA", idHabitacion);
                } else if (nuevoEstado == EstadoReserva.CANCELADA) {
                    // Cancelacion — habitacion vuelve a disponible
                    log.info("Cancelacion: cambiando habitacion id: {} a DISPONIBLE", idHabitacion);
                    habitacionClient.cambiarEstadoHabitacion(idHabitacion, 1L); // 1 = DISPONIBLE
                } else {
                    throw new IllegalStateException(
                            "Transicion invalida: no se puede pasar de CONFIRMADA a " + nuevoEstado);
                }
            }
            case EN_CURSO -> {
                if (nuevoEstado == EstadoReserva.FINALIZADA) {
                    // Check-out — habitación vuelve a DISPONIBLE
                    log.info("Check-out: cambiando habitacion id: {} a DISPONIBLE", idHabitacion);
                    habitacionClient.cambiarEstadoHabitacion(idHabitacion, 1L); // 1 = DISPONIBLE
                } else {
                    throw new IllegalStateException(
                            "Transicion invalida: no se puede pasar de EN_CURSO a " + nuevoEstado);
                }
            }
            default -> throw new IllegalStateException(
                    "No se puede cambiar el estado de una reserva en estado: " + estadoActual);
        }
    }

	@Override
	public ReservaResponse cambiarEstado(Long idReserva, Long idEstado) {
	    log.info("Cambiando estado de reserva id: {} al estado id: {}", idReserva, idEstado);

	    Reserva reserva = obtenerReservaActivaPorIdOException(idReserva);
	    EstadoReserva estadoActual = reserva.getEstadoReserva();
	    EstadoReserva nuevoEstado = EstadoReserva.obtenerEstadoReservaPorCodigo(idEstado);

	    validarTransicionEstado(estadoActual, nuevoEstado, reserva.getIdHabitacion());
	    reserva.cambiarEstado(nuevoEstado);

	    log.info("Estado de reserva id: {} cambio de {} a {}", idReserva, estadoActual, nuevoEstado);
	    return enriquecer(reserva);
	}
	
	// metodo helper privado
	private ReservaResponse enriquecer(Reserva reserva) {
	    HuespedResponse huesped = 
	        huespedClient.obtenerHuespedActivo(reserva.getIdHuesped());
	    HabitacionResponse habitacion = 
	        habitacionClient.obtenerHabitacion(reserva.getIdHabitacion());
	    return reservaMapper.entidadResponse(reserva, huesped, habitacion);
	}
	
}