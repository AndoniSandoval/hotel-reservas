package com.aa.reservas.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aa.common.controllers.CommonController;
import com.aa.reservas.dto.ReservaRequest;
import com.aa.reservas.dto.ReservaResponse;
import com.aa.reservas.dto.ReservaUpdateRequest;
import com.aa.reservas.services.ReservaService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@RestController
public class ReservaController extends CommonController<ReservaRequest, ReservaResponse, ReservaService>{

	public ReservaController(ReservaService service) {
		super(service);
	}
	
	// Sobreescribe el PUT heredado de CommonController
    @PutMapping("/{id}")
    //@Override
    public ResponseEntity<ReservaResponse> actualizar(
            @PathVariable @Positive(message = "El id debe ser positivo") Long id,
            @Valid @RequestBody ReservaUpdateRequest request) {
        return ResponseEntity.ok(service.actualizarFechas(request, id));
    }

    // PATCH  — check-in, check-out, cancelacion
    @PatchMapping("/{idReserva}/estado/{idEstado}")
    public ResponseEntity<ReservaResponse> cambiarEstado(
            @PathVariable @Positive(message = "El id debe ser positivo") Long idReserva,
            @PathVariable @Positive(message = "El id de estado debe ser positivo") Long idEstado) {
        return ResponseEntity.ok(service.cambiarEstado(idReserva, idEstado));
    }
    
    @GetMapping("/huesped/{idHuesped}/en-curso")
    public ResponseEntity<Boolean> huespedTieneReservasEnCurso(
            @PathVariable @Positive Long idHuesped) {
        return ResponseEntity.ok(service.huespedTieneReservasEnCurso(idHuesped));
    }

    @GetMapping("/habitacion/{idHabitacion}/en-curso")
    public ResponseEntity<Boolean> habitacionTieneReservasEnCurso(
            @PathVariable @Positive Long idHabitacion) {
        return ResponseEntity.ok(service.habitacionTieneReservasEnCurso(idHabitacion));
    }
}
