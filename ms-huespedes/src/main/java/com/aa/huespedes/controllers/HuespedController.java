package com.aa.huespedes.controllers;

import com.aa.common.controllers.CommonController;
import com.aa.common.dto.huespedes.HuespedRequest;
import com.aa.common.dto.huespedes.HuespedResponse;
import com.aa.huespedes.services.HuespedService;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class HuespedController extends CommonController<HuespedRequest, HuespedResponse, HuespedService> {

    public HuespedController(HuespedService service) {
        super(service);
    }

    @GetMapping("/id-huesped/{id}")
    public ResponseEntity<HuespedResponse> obtenerHuespedActivo(
            @PathVariable @Positive(message = "El ID debe ser positivo") Long id) {
        return ResponseEntity.ok(service.obtenerHuespedActivo(id));
    }
}