package com.app.cuenta.controllers;

import com.app.cuenta.dtos.UsuarioDto;
import com.app.cuenta.models.entitys.Usuario;
import com.app.cuenta.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/{id}")
    public ResponseEntity<?> searchById(@PathVariable Integer id) {
        UsuarioDto u = usuarioService.searchById(id);
        if(u!=null) {
            return ResponseEntity.status(HttpStatus.OK).body(u);
        }
        else
            return ResponseEntity.status(HttpStatus.OK).body("no se encontraron resultados");
    }

    @PostMapping()
    public ResponseEntity<?> persist(@RequestBody Usuario u) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(u));
        }
        catch(Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("asegure de mandar bien el formato");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable int id) {
        UsuarioDto u = usuarioService.deleteById(id);
        if(u!=null) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "borrado");
            response.put("usuario", u);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no encontrado");
    }
}
