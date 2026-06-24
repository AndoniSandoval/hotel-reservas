package com.aa.huespedes.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.aa.common.enums.EstadoRegistro;
import com.aa.common.exceptions.RecursoNoEncontradoException;
import com.aa.huespedes.dto.HuespedRequest;
import com.aa.huespedes.dto.HuespedResponse;
import com.aa.huespedes.entities.Huesped;
import com.aa.huespedes.mappers.HuespedMapper;
import com.aa.huespedes.repositories.HuespedRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class HuespedServiceImpl implements HuespedService{
	
	private final HuespedRepository huespedRepository;
	private final HuespedMapper huespedMapper;

	@Override
	public List<HuespedResponse> listar() {
		log.info("Listando todos los huespedes activos");
        return huespedRepository.findByEstadoRegistro(EstadoRegistro.ACTIVO)
                .stream()
                .map(huespedMapper::entidadResponse)
                .toList();
	}

	@Override
	public HuespedResponse obtenerPorId(Long id) {
		log.info("Buscando huesped activo con id: {}", id);
        return huespedMapper.entidadResponse(obtenerHuespedActivoOException(id));
	}

	@Override
	public HuespedResponse registrar(HuespedRequest request) {
		 log.info("Registrando nuevo huesped: {} {}", request.nombre(), request.apellidoPaterno());

	        validarDatosUnicos(request);

	        Huesped huesped = huespedMapper.requestEntidad(request);

	        huespedRepository.save(huesped);

	        log.info("Huesped registrado exitosamente con id: {}", huesped.getId());

	        return huespedMapper.entidadResponse(huesped);
	}

	@Override
	public HuespedResponse actualizar(HuespedRequest request, Long id) {
		 log.info("Actualizando huesped con id: {}", id);

	        Huesped huesped = obtenerHuespedActivoOException(id);

	        validarCambiosUnicos(request, id);

	        huesped.actualizar(
	                request.nombre(),
	                request.apellidoPaterno(),
	                request.apellidoMaterno(),
	                request.email(),
	                request.telefono(),
	                request.documento(),
	                request.nacionalidad()
	        );

	        log.info("Huesped actualizado exitosamente con id: {}", id);

	        return huespedMapper.entidadResponse(huesped);
	}

	@Override
	public void eliminar(Long id) {
		 log.info("Eliminando huesped con id: {}", id);

	        Huesped huesped = obtenerHuespedActivoOException(id);

	        validarHuespedSinReservasEnCurso(id);

	        huesped.eliminar();

	        log.info("Huesped eliminado logicamente con id: {}", id);
		
	}

	@Override
	public HuespedResponse obtenerHuespedActivo(Long id) {
		 log.info("Verificando existencia de huesped con id: {} para consumo externo", id);
	        return huespedMapper.entidadResponse(
	                huespedRepository.findByIdAndEstadoRegistro(id, EstadoRegistro.ACTIVO)
	                        .orElseThrow(() -> new RecursoNoEncontradoException(
	                                "Huesped activo no encontrado con id: " + id)));
	}
	
	
	private Huesped obtenerHuespedActivoOException(Long id) {
        log.info("Buscando huesped con estado {} con id: {}", EstadoRegistro.ACTIVO, id);
        return huespedRepository.findByIdAndEstadoRegistro(id, EstadoRegistro.ACTIVO)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Huesped activo no encontrado con id: " + id));
    }

    private void validarDatosUnicos(HuespedRequest request) {
        log.info("Validando email unico...");
        if (huespedRepository.existsByEmailAndEstadoRegistro(
                request.email().trim(), EstadoRegistro.ACTIVO))
            throw new IllegalArgumentException(
                    "Ya existe un huesped activo con el email: " + request.email());

        log.info("Validando telefono unico...");
        if (huespedRepository.existsByTelefonoAndEstadoRegistro(
                request.telefono().trim(), EstadoRegistro.ACTIVO))
            throw new IllegalArgumentException(
                    "Ya existe un huesped activo con el telefono: " + request.telefono());

        log.info("Validando documento unico...");
        if (huespedRepository.existsByDocumentoAndEstadoRegistro(
                request.documento().trim(), EstadoRegistro.ACTIVO))
            throw new IllegalArgumentException(
                    "Ya existe un huesped activo con el documento: " + request.documento());
    }

    private void validarCambiosUnicos(HuespedRequest request, Long id) {
        log.info("Validando email unico en actualizacion...");
        if (huespedRepository.existsByEmailAndEstadoRegistroAndIdNot(
                request.email().trim(), EstadoRegistro.ACTIVO, id))
            throw new IllegalArgumentException(
                    "Ya existe un huesped activo con el email: " + request.email());

        log.info("Validando telefono unico en actualizacion...");
        if (huespedRepository.existsByTelefonoAndEstadoRegistroAndIdNot(
                request.telefono().trim(), EstadoRegistro.ACTIVO, id))
            throw new IllegalArgumentException(
                    "Ya existe un huesped activo con el telefono: " + request.telefono());

        log.info("Validando documento unico en actualizacion...");
        if (huespedRepository.existsByDocumentoAndEstadoRegistroAndIdNot(
                request.documento().trim(), EstadoRegistro.ACTIVO, id))
            throw new IllegalArgumentException(
                    "Ya existe un huesped activo con el documento: " + request.documento());
    }

    private void validarHuespedSinReservasEnCurso(Long id) {
        // TODO: implementar cuando ms-reservas esté listo
        // Se conectará via Feign Client para verificar que el huésped
        // no tenga reservas con estado EN_CURSO antes de eliminarlo
        log.info("Validando que huesped con id: {} no tenga reservas EN_CURSO", id);
    }

}
