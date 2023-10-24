package com.app.cuenta.controllers;

import com.app.cuenta.dtos.CuentaDto;
import com.app.cuenta.models.entitys.Cuenta;
import com.app.cuenta.services.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("cuenta")
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;

    @GetMapping("/{id}")
    public ResponseEntity<?> searchById(@PathVariable Integer id) {
        CuentaDto c = cuentaService.searchById(id);
        if(c!=null) {
            return ResponseEntity.status(HttpStatus.OK).body(c);
        }
        else
            return ResponseEntity.status(HttpStatus.OK).body("no se encontraron resultados");
    }

    @PostMapping()
    public ResponseEntity<?> persist(@RequestBody Cuenta c) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(cuentaService.save(c));
        }
        catch(Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("asegure de mandar bien el formato");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable int id) {
        CuentaDto c = cuentaService.deleteById(id);
        if(c!=null) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "borrado");
            response.put("cuenta", c);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no encontrado");
    }
}
