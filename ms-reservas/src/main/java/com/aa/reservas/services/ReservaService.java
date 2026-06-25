package com.aa.reservas.services;

import org.springframework.stereotype.Service;

import com.aa.common.services.CrudService;
import com.aa.reservas.dto.ReservaRequest;
import com.aa.reservas.dto.ReservaResponse;

@Service
public interface ReservaService extends CrudService<ReservaRequest, ReservaResponse> {

}
