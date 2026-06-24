package com.aa.habitaciones.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aa.habitaciones.entities.Habitaciones;
import com.aa.common.enums.EstadoRegistro;
import java.util.List;
import java.util.Optional;


public interface HabitacionesRepository extends JpaRepository<Habitaciones, Long> {
	boolean existsByNumeroHabitacionAndEstadoRegistro(Integer numeroHabitacion, EstadoRegistro estadoRegistro);
	
	boolean existsByNumeroHabitacionAndIdHabitacionNotAndEstadoRegistro(Integer numeroHabitacion, Long idHabitacion, EstadoRegistro estadoRegistro);

   List<Habitaciones> findByEstadoRegistro(EstadoRegistro estadoRegistro);
   
   Optional<Habitaciones> findByIdHabitacionAndEstadoRegistro(Long idHabitacion, EstadoRegistro estadoRegistro);
}
