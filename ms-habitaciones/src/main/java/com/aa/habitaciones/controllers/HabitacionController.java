package com.aa.habitaciones.controllers;

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

}
