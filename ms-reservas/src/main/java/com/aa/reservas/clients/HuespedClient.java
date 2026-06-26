package com.aa.reservas.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.aa.common.dto.huespedes.HuespedResponse;

@FeignClient(name = "ms-huespedes")
public interface HuespedClient {

	 @GetMapping("/huespedes/id-huesped/{id}")
	    HuespedResponse obtenerHuespedActivo(@PathVariable Long id);
}
