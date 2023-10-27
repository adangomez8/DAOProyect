package com.app.monopatin.controllers;

import com.app.monopatin.dtos.MonopatinDto;
import com.app.monopatin.entitys.Monopatin;
import com.app.monopatin.services.MonopatinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("monopatin")
public class MonopatinController {

    @Autowired
    private MonopatinService service;

    @GetMapping("")
    public ResponseEntity<?>getAll(){

        List<MonopatinDto> dto=service.getAll();

        if(dto!=null) {
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al listar monopatines");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?>getById(@PathVariable Integer id){

        MonopatinDto m= service.getById(id);

        if(m!=null) {
            return ResponseEntity.status(HttpStatus.OK).body(m);
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error");
        }
    }

    @PostMapping("")
    public ResponseEntity<?>create(@RequestBody Monopatin m) throws Exception {
        MonopatinDto aux = service.create(m);
        if(aux!=null){
            return ResponseEntity.status(HttpStatus.CREATED).body(aux);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al crear monopatin, ingrese campos correctos");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?>update(@PathVariable Integer id, @RequestBody MonopatinDto m){

        try {
            service.update(id, m);
            return ResponseEntity.status(HttpStatus.OK).body(m);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("Error al modificar.Verifique que el monopatin exista");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?>delete(@PathVariable Integer id){

        try {
            service.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Monopatin eliminado");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error. El monopatin que desea eliminar no existe");
        }
    }

}
