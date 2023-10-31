package com.Microservicios.Mantenimiento;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mantenimiento")
public class MantenimientoController {
    @GetMapping("")
    public String get(){
        System.out.println("hola");
        return "hola";
    }
}
