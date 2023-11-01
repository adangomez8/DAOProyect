package com.Microservicios.Mantenimiento.Services;

import com.Microservicios.Mantenimiento.Dto.DtoMonopatin;
import com.Microservicios.Mantenimiento.Dto.ReporteKM;
import com.Microservicios.Mantenimiento.Dto.ReporteWithPause;
import com.Microservicios.Mantenimiento.Dto.ReporteWithoutPause;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class MantenimientoService {
    @Autowired
    RestTemplate template;

    public List<ReporteKM> generateReportByKM(){
        List<ReporteKM> aux = new ArrayList<>();

        List<DtoMonopatin> ldm = getAllMonopatines();
        for(DtoMonopatin dm: ldm){
            aux.add(new ReporteKM(dm.getId(),dm.getKilometros()));
        }
        return aux;
    }
    public List<ReporteWithPause> generateReportWithPause(){
        List<ReporteWithPause> aux = new ArrayList<>();
        List<DtoMonopatin> monopatines = getAllMonopatines();

        for(DtoMonopatin dm: monopatines){
            aux.add(new ReporteWithPause(dm.getId(),dm.getTiempoEnPausa()));
        }
        return aux;
    }
    public List<ReporteWithoutPause> generateReportWithoutPause(){
        List<ReporteWithoutPause> aux = new ArrayList<>();
        List<DtoMonopatin> monopatines = getAllMonopatines();

        for(DtoMonopatin dm: monopatines){
            aux.add(new ReporteWithoutPause(dm.getId(),dm.getTiempoEnUso()));
        }
        return aux;
    }
    private List<DtoMonopatin> getAllMonopatines(){
        String url = "http://localhost:8081/monopatin";
        ResponseEntity<List<DtoMonopatin>> response = template.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<DtoMonopatin>>() {}
        );
        return response.getBody();
    }
}
