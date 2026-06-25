package com.aa.huespedes.services;

import com.aa.common.dto.huespedes.HuespedRequest;
import com.aa.common.dto.huespedes.HuespedResponse;
import com.aa.common.services.CrudService;

public interface HuespedService extends CrudService<HuespedRequest, HuespedResponse>{
	
	// /id-huesped/{id}
    HuespedResponse obtenerHuespedActivo(Long id);
}
