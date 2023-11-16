package com.MicroservicioViaje.controllers;

import com.MicroservicioViaje.Dto.ViajeDto;
import com.MicroservicioViaje.entities.Viaje;
import com.MicroservicioViaje.services.ViajeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/viaje")
public class ViajeController {
    @Autowired
    private ViajeService viajeService;

    @GetMapping("")
    public ResponseEntity<?> getViajes(){
        List<ViajeDto> aux = viajeService.getAll();
        if(aux != null){
            return ResponseEntity.status(HttpStatus.OK).body(aux);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al listar todos los viajes");
        }
    }
    
    @PutMapping("/actualizar")
    public ResponseEntity<?>updatePrecio(@RequestParam(name="precio")double precio,@RequestParam(name="fecha")String fecha){
    	try {
    		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaConvertida = dateFormat.parse(fecha);
    		viajeService.updatePrecio(precio, fechaConvertida);
    		return ResponseEntity.status(HttpStatus.OK).body("Precios actualizados exitosamente");
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error.Precios no actualizados");
    	}
    }
    
    @GetMapping("/recaudacion")
    public ResponseEntity<?>getRecaudacion(@RequestParam(name="anio")Date anio,@RequestParam(name="mesIni")Date mesIni,@RequestParam(name="mesFin")Date mesFin){
    	try {
    		double recaudacion= viajeService.getRecaudacion(anio, mesIni, mesFin);
    		return ResponseEntity.status(HttpStatus.OK).body(recaudacion);
    	}
    	catch(Exception e) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error en generar la consulta");
    	}
    }
    
    @GetMapping("/{id}")

    public ResponseEntity<?> getByID(@PathVariable int id){

        ViajeDto aux = viajeService.getById(id);

        if(aux != null){
            return ResponseEntity.status(HttpStatus.OK).body(aux);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Viaje no encontrado");
        }
    }
    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody Viaje v){
        ViajeDto aux = viajeService.create(v);
        if(aux!=null){
            return ResponseEntity.status(HttpStatus.CREATED).body(aux);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al crear viaje, ingrese campos correctos");
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Viaje v, @PathVariable int id){
        ViajeDto aux = viajeService.update(v);
        if(aux!=null){
            return ResponseEntity.status(HttpStatus.OK).body(aux);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El viaje no existe o ingresa incorrectamente los campos");
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        ViajeDto aux = viajeService.delete(id);
        if(aux!=null){
            return ResponseEntity.status(HttpStatus.OK).body(aux);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El viaje no existe");
        }
    }

}
