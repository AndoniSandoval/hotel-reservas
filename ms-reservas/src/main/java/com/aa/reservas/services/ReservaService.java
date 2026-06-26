package com.aa.reservas.services;

import com.aa.common.services.CrudService;
import com.aa.reservas.dto.ReservaRequest;
import com.aa.reservas.dto.ReservaResponse;
import com.aa.reservas.dto.ReservaUpdateRequest;

public interface ReservaService extends CrudService<ReservaRequest, ReservaResponse> {
	
	// sobrescribi el actualizar de CrudService — no se usa en reservas
    @Override
    default ReservaResponse actualizar(ReservaRequest request, Long id) {
        throw new UnsupportedOperationException(
            "Use actualizarFechas() para modificar una reserva");
    }
    
    ReservaResponse actualizarFechas(ReservaUpdateRequest request, Long id);
    ReservaResponse cambiarEstado(Long idReserva, Long idEstado);
    boolean huespedTieneReservasEnCurso(Long idHuesped);
    boolean habitacionTieneReservasEnCurso(Long idHabitacion);
}
