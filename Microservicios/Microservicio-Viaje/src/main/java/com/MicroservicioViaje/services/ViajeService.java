package com.MicroservicioViaje.services;

import com.MicroservicioViaje.Dto.ViajeDto;
import com.MicroservicioViaje.entities.Viaje;
import com.MicroservicioViaje.repositories.ViajeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ViajeService {
    @Autowired
    private ViajeRepository viajeRepository;

    @Transactional
    public ViajeDto     getById(@PathVariable Integer id){
        Optional<Viaje> v = viajeRepository.findById(id);
        if(v.isPresent()){
            return transformDTO(v.get());
        }else {
            return null;
        }
    }
    @Transactional
    public List<ViajeDto> getAll(){
        List<ViajeDto> aux = new ArrayList<>();
        for(Viaje v: viajeRepository.findAll()){
            aux.add(transformDTO(v));
        }
        return aux;
    }

    @Transactional
    public ViajeDto create(Viaje v){
        try{
            viajeRepository.save(v);
            return transformDTO(v);
        }catch (Exception exc){
            return null;
        }
    }
    @Transactional
    public ViajeDto update(Viaje v){
        Optional<Viaje> viajeViejo = viajeRepository.findById(v.getId_viaje());
        if(!viajeViejo.isPresent()){
            return null;
        }else{
            try {
                Viaje aux = viajeRepository.save(v);
                return transformDTO(aux);
            }catch (Exception e){
                return null;
            }
        }
    }
    @Transactional
    public ViajeDto delete(int id){
        Optional<Viaje> opc = viajeRepository.findById(id);
        if(opc.isPresent()){
            viajeRepository.delete(opc.get());
            return transformDTO(opc.get());
        }else{
            return null;
        }
    }
    private ViajeDto transformDTO(Viaje v){
        return new ViajeDto(v.getId_viaje(), v.getId_cuenta(),
                v.getId_monopatin(),v.getFecha_inicio(),v.getFecha_fin(),
                v.getKm_recorridos(),v.getPrecio(),v.getTarifa_extra());

    }
}
