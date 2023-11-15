package com.app.monopatin.controllers;

import com.app.monopatin.dtos.MonopatinDto;
import com.app.monopatin.dtos.ViajeDto;
import com.app.monopatin.entitys.Monopatin;
import com.app.monopatin.entitys.Parada;
import com.app.monopatin.services.MonopatinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("monopatin")
public class MonopatinController {

    @Autowired
    private MonopatinService service;
    
    @PostMapping("/{monopatinId}/asociar-viaje/{viajeId}")
    public ResponseEntity<String> asociarViajeAMonopatin(
    		@RequestBody ViajeDto viaje,
            @PathVariable Integer viajeId,
            @PathVariable Integer monopatinId
    ) {
        try {
            service.asociarViajeMonopatin(viaje,viajeId, monopatinId);
            return ResponseEntity.ok("Viaje asociado exitosamente al monopatín.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al asociar el viaje al monopatín: " + e.getMessage());
        }
    }
    
    @GetMapping("/cant-viajes")
    public ResponseEntity<?>monopatinByViaje(@RequestParam(name="cantidad")int cantidad){
    	try {
    		List<Monopatin>m=service.monopatinByViaje(cantidad);
    		return ResponseEntity.status(HttpStatus.OK).body(m);
    	}
    	catch(Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al realizar la consulta");
    	}
    }
    
    @GetMapping("/disponibles")
    
    public ResponseEntity<?>getCantMonopatinDisponible(){
    	try {
    		Integer cantidad=service.getCantMonopatinDisponible();
    		return ResponseEntity.status(HttpStatus.OK).body(cantidad);
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error en realizar la consulta");
    	}
    }
    
    @GetMapping("/mantenimiento")
    
    public ResponseEntity<?>getCantMonopatinMantenimiento(){
    	try {
    		int cantidad=service.getCantMonopatinMantenimiento();
    		return ResponseEntity.status(HttpStatus.OK).body(cantidad);
    	}
    	catch(Exception e) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error en realizar la consulta");
    	}
    }

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

    @PostMapping("/{id}")
    public ResponseEntity<?>create(@RequestBody Monopatin m, @PathVariable Integer id) throws Exception {
        try{
        	service.create(m,id);
            return ResponseEntity.status(HttpStatus.CREATED).body(m);
        }catch(Exception e){
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

    @GetMapping("/cercanos/{id}/{distancia}")
    public ResponseEntity<?>getMonopatinesCercanosAMonopatin(@PathVariable Integer id, @PathVariable double distancia){

        MonopatinDto m= service.getById(id);

        if(m!=null) {
            List<MonopatinDto> dto = service.getMonopatinesCercanosAMonopatin(id, distancia);

            if (dto != null) {
                return ResponseEntity.status(HttpStatus.OK).body(dto);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se encontraron monopatines cerca");
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al listar monopatines");
    }
}
