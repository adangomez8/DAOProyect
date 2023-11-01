package com.Microservicios.Mantenimiento.Controllers;

import com.Microservicios.Mantenimiento.Dto.ReporteKM;
import com.Microservicios.Mantenimiento.Dto.ReporteWithPause;
import com.Microservicios.Mantenimiento.Dto.ReporteWithoutPause;
import com.Microservicios.Mantenimiento.Services.MantenimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/mantenimiento")
public class MantenimientoController {
    @Autowired
    MantenimientoService service;
    @GetMapping("/reportekm")
    public ResponseEntity<?> getReporteKM(){
        List<ReporteKM> res = service.generateReportByKM();
        if(res.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron monopatines");
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(res);
        }
    }
    @GetMapping("/reporteconpausa")
    public ResponseEntity<?> getReporteConPausa(){
        List<ReporteWithPause> res = service.generateReportWithPause();
        if(res.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron monopatines");
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(res);
        }
    }
    @GetMapping("/reportesinpausa")
    public ResponseEntity<?> getReporteSinPausa(){
        List<ReporteWithoutPause> res = service.generateReportWithoutPause();
        if(res.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron monopatines");
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(res);
        }
    }
}
