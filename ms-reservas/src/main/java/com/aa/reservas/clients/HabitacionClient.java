package com.aa.reservas.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.aa.common.dto.habitaciones.HabitacionResponse;

@FeignClient(name = "ms-habitaciones")
public interface HabitacionClient {

	@GetMapping("/habitaciones/id-habitacion/{id}")
    HabitacionResponse obtenerHabitacion(@PathVariable Long id);

    @PutMapping("/habitaciones/{id}/estado/{idEstado}")
    void cambiarEstadoHabitacion(@PathVariable Long id, @PathVariable Long idEstado);
}
