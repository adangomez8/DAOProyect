package com.app.monopatin.controllers;

import com.app.monopatin.dtos.MonopatinDto;
import com.app.monopatin.models.entitys.Monopatin;
import com.app.monopatin.services.MonopatinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("monopatin")
public class MonopatinController {

    @Autowired
    private MonopatinService monopatinService;

    @GetMapping("/{id}")
    public ResponseEntity<?> searchById(@PathVariable Integer id) {
        MonopatinDto m = monopatinService.searchById(id);
        if(m!=null) {
            return ResponseEntity.status(HttpStatus.OK).body(m);
        }
        else
            return ResponseEntity.status(HttpStatus.OK).body("no se encontraron resultados");
    }

    @PostMapping()
    public ResponseEntity<?> persist(@RequestBody Monopatin m) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(monopatinService.save(m));
        }
        catch(Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("asegure de mandar bien el formato");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable int id) {
        MonopatinDto m = monopatinService.deleteById(id);
        if(m!=null) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "borrado");
            response.put("monopatin", m);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no encontrado");
    }

}
