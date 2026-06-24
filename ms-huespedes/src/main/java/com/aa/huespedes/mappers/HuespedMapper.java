package com.aa.huespedes.mappers;

import org.springframework.stereotype.Component;

import com.aa.common.mappers.CommonMapper;
import com.aa.huespedes.dto.HuespedRequest;
import com.aa.huespedes.dto.HuespedResponse;
import com.aa.huespedes.entities.Huesped;

@Component
public class HuespedMapper implements CommonMapper<HuespedRequest, HuespedResponse, Huesped>{

	@Override
	public Huesped requestEntidad(HuespedRequest request) {
		return Huesped.builder()
                .nombre(request.nombre().trim())
                .apellidoPaterno(request.apellidoPaterno().trim())
                .apellidoMaterno(request.apellidoMaterno().trim())
                .email(request.email().trim())
                .telefono(request.telefono().trim())
                .documento(request.documento().trim())
                .nacionalidad(request.nacionalidad().trim())
                .build();
	}

	@Override
	public HuespedResponse entidadResponse(Huesped huesped) {
		return new HuespedResponse(
                huesped.getId(),
                huesped.getNombre(),
                huesped.getApellidoPaterno(),
                huesped.getApellidoMaterno(),
                huesped.getEmail(),
                huesped.getTelefono(),
                huesped.getDocumento(),
                huesped.getNacionalidad(),
                huesped.getEstadoRegistro(),
                huesped.getFechaCreacion(),
                huesped.getFechaActualizacion()
        );
	}
	
	

}
