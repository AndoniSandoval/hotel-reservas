package com.aa.huespedes.controllers;

import com.aa.common.controllers.CommonController;
import com.aa.huespedes.dto.HuespedRequest;
import com.aa.huespedes.dto.HuespedResponse;
import com.aa.huespedes.services.HuespedService;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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