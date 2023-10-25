package com.app.monopatin.controllers;

import com.app.monopatin.dtos.ParadaDto;
import com.app.monopatin.models.entitys.Parada;
import com.app.monopatin.services.ParadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("parada")
public class ParadaController {

    @Autowired
    private ParadaService paradaService;

    @GetMapping("/{id}")
    public ResponseEntity<?> searchById(@PathVariable Integer id) {
        ParadaDto p = paradaService.searchById(id);
        if(p!=null) {
            return ResponseEntity.status(HttpStatus.OK).body(p);
        }
        else
            return ResponseEntity.status(HttpStatus.OK).body("no se encontraron resultados");
    }

    @PostMapping()
    public ResponseEntity<?> persist(@RequestBody Parada p) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(paradaService.save(p));
        }
        catch(Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("asegure de mandar bien el formato");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable int id) {
        ParadaDto p = paradaService.deleteById(id);
        if(p!=null) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "borrado");
            response.put("parada", p);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no encontrado");
    }
}
