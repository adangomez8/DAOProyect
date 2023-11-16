package com.app.monopatin.services;

import com.app.monopatin.dtos.MonopatinDto;
import com.app.monopatin.dtos.ViajeDto;
import com.app.monopatin.entitys.Monopatin;
import com.app.monopatin.entitys.Parada;
import com.app.monopatin.repositorys.MonopatinRepository;
import com.app.monopatin.repositorys.ParadaRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MonopatinService {

    @Autowired
    private MonopatinRepository repository;
    @Autowired
    private ParadaRepository pRepository;
    
    @Autowired
    RestTemplate template;
    
    public void asociarViajeMonopatin(ViajeDto viaje,Integer viajeId,Integer monopatinId) {
    	ResponseEntity<?>response= template.getForEntity("http://localhost:8082/api/viaje/"+viajeId, ViajeDto.class,viajeId);
    	if(response.getStatusCode().is2xxSuccessful()) {
    		viaje= (ViajeDto) response.getBody();
    		Optional<Monopatin> monopatin= repository.findById(monopatinId);
    		
    		if(monopatin.isPresent()) {
    			Monopatin m=monopatin.get(); 
    			m.getViajeIds().add(viaje.getId_viaje());
    			repository.save(m);
    		}
    	}
    }
    
    @Transactional
    
    public List<Monopatin> monopatinByViaje(int cantidad) {
    	return repository.findMonopatinesOrderByCantidadViajes(cantidad);
    }
    
    @Transactional
    
    public Integer getCantMonopatinDisponible() {
    	return repository.getCantMonopatinDisponible();
    }
    
    @Transactional
    
    public int getCantMonopatinMantenimiento() {
    	return repository.getCantMonopatinMantenimiento();
    }

    @Transactional
    public MonopatinDto getById(@PathVariable Integer id) {

        Optional<Monopatin> monopatin= repository.findById(id);

        if(monopatin.isPresent()) {
        	return this.convertDto(monopatin.get());
        }
        else {
        	return null;
        }
    }

    @SuppressWarnings("deprecation")
	@Transactional
    public void create(Monopatin monopatin,@PathVariable Integer id) throws Exception {
        try{
        	if(!repository.existsById(monopatin.getId())) {
        	Parada parada=pRepository.getById(id);
        	System.out.println("entro");
        	Monopatin m= new Monopatin(monopatin.getId(),monopatin.getEstado(),monopatin.getLatitud(),monopatin.getLongitud(),parada);
            repository.save(m);
        	}
            //this.convertDto(monopatin,id);
        }catch (Exception e){
        	e.printStackTrace();
        	System.out.println("hola");
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public void delete(@PathVariable int id) {

        Optional<Monopatin> optionalMonopatin = repository.findById(id);

        if (optionalMonopatin.isPresent()) {
            Monopatin monopatin = optionalMonopatin.get();
            repository.deleteById(id);
            this.convertDto(monopatin);
        }
    }

    @Transactional
    public List<MonopatinDto>getAll(){
        List<Monopatin> monopatines = repository.findAll();

        List<MonopatinDto> dtos = monopatines.stream()
                .map(monopatin -> new MonopatinDto(monopatin.getId(), monopatin.getEstado(), monopatin.getKilometros() ,monopatin.getLatitud(), monopatin.getLongitud(), monopatin.getTiempoEnUso(), monopatin.getTiempoEnPausa(),monopatin.getParada()))
                .collect(Collectors.toList());

        return dtos;
    }

    @Transactional
    public void update(int id, MonopatinDto monopatinDto) {

        Optional<Monopatin> optionalMonopatin = repository.findById(id);

        if(optionalMonopatin!=null) {
            Monopatin monopatin = optionalMonopatin.get();

            monopatin.setEstado(monopatinDto.getEstado());
            monopatin.setKilometros(monopatinDto.getKilometros());
            monopatin.setLatitud(monopatinDto.getLatitud());
            monopatin.setLongitud(monopatinDto.getLongitud());
            monopatin.setTiempoEnUso(monopatinDto.getTiempoEnUso());
            monopatin.setTiempoEnPausa(monopatinDto.getTiempoEnPausa());

            repository.save(monopatin);
        }
    }

    @Transactional
    public List<MonopatinDto>getMonopatinesCercanosAMonopatin(int idMonopatin, double distancia){

        if(repository.findById(idMonopatin)!=null) {
            List<Monopatin> monopatines = repository.getMonopatinesCercanosAMonopatin(idMonopatin, distancia);

            List<MonopatinDto> monopatinesCercanos = monopatines.stream()
                    .map(monopatin -> new MonopatinDto(monopatin.getId(), monopatin.getEstado(), monopatin.getKilometros(), monopatin.getLatitud(), monopatin.getLongitud(), monopatin.getTiempoEnUso(), monopatin.getTiempoEnPausa(), monopatin.getParada()))
                    .collect(Collectors.toList());

            return monopatinesCercanos;
        } else{
            return null;
        }
    }

    private MonopatinDto convertDto(Monopatin monopatin) {
    	

        return new MonopatinDto(monopatin.getId(), monopatin.getEstado(), monopatin.getKilometros() ,monopatin.getLatitud(), monopatin.getLongitud(), monopatin.getTiempoEnUso(), monopatin.getTiempoEnPausa(),monopatin.getParada());
    }

    public List<MonopatinDto> getAllIn(List<Integer> listaID) {
        List<Monopatin> monopatines = repository.getAllInList(listaID);
        List<MonopatinDto> aux = new ArrayList<>();
        for(Monopatin m:monopatines){
            aux.add(convertDto(m));
        }
        return aux;
    }
}
