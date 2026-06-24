package com.aa.huespedes.services;

import com.aa.common.services.CrudService;
import com.aa.huespedes.dto.HuespedRequest;
import com.aa.huespedes.dto.HuespedResponse;

public interface HuespedService extends CrudService<HuespedRequest, HuespedResponse>{
	
	// /id-huesped/{id}
    HuespedResponse obtenerHuespedActivo(Long id);
}
