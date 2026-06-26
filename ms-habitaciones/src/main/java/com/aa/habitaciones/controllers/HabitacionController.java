package com.aa.habitaciones.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aa.common.controllers.CommonController;
import com.aa.common.dto.habitaciones.HabitacionRequest;
import com.aa.common.dto.habitaciones.HabitacionResponse;
import com.aa.habitaciones.services.HabitacionesService;
@RestController
public class HabitacionController extends CommonController<HabitacionRequest, HabitacionResponse, HabitacionesService> {

	public HabitacionController(HabitacionesService service) {
		super(service);
		// TODO Auto-generated constructor stub
	}
	
	@PutMapping("/{id}/estado/{idEstado}")
	public ResponseEntity<Void> cambiarEstado(
	        @PathVariable Long id,
	        @PathVariable Long idEstado) {

	    service.cambiarEstado(id, idEstado);

	    return ResponseEntity.noContent().build();
	}

}
