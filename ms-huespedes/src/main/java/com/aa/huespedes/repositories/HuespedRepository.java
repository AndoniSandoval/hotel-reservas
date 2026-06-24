package com.aa.huespedes.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aa.common.enums.EstadoRegistro;
import com.aa.huespedes.entities.Huesped;

public interface HuespedRepository extends JpaRepository<Huesped, Long>{
	
	// listar ACTIVOS
    List<Huesped> findByEstadoRegistro(EstadoRegistro estadoRegistro);

    // buscar por ID si esta ACTIVO
    Optional<Huesped> findByIdAndEstadoRegistro(Long id, EstadoRegistro estadoRegistro);

    boolean existsByEmailAndEstadoRegistro(String email, EstadoRegistro estadoRegistro);
    boolean existsByTelefonoAndEstadoRegistro(String telefono, EstadoRegistro estadoRegistro);
    boolean existsByDocumentoAndEstadoRegistro(String documento, EstadoRegistro estadoRegistro);

    boolean existsByEmailAndEstadoRegistroAndIdNot(String email, EstadoRegistro estadoRegistro, Long id);
    boolean existsByTelefonoAndEstadoRegistroAndIdNot(String telefono, EstadoRegistro estadoRegistro, Long id);
    boolean existsByDocumentoAndEstadoRegistroAndIdNot(String documento, EstadoRegistro estadoRegistro, Long id);
    
}
