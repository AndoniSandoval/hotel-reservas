package com.aa.reservas.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aa.common.enums.EstadoRegistro;
import com.aa.common.enums.EstadoReserva;
import com.aa.reservas.entities.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Long>{
	
	// listar activos
    List<Reserva> findByEstadoRegistro(EstadoRegistro estadoRegistro);

    // buscar por id solo si esta activo
    Optional<Reserva> findByIdReservaAndEstadoRegistro(Long id, EstadoRegistro estadoRegistro);

    // verificar si un huesped tiene reservas en curso
    // ms-huespedes via Feign 
    boolean existsByIdHuespedAndEstadoReserva(Long idHuesped, EstadoReserva estadoReserva);

    // verificar si una habitacion tiene reservas en curso
    // ms-habitaciones via Feign
    boolean existsByIdHabitacionAndEstadoReserva(Long idHabitacion, EstadoReserva estadoReserva);
}
