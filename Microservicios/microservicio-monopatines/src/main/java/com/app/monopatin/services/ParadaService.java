package com.app.monopatin.services;

import com.app.monopatin.dtos.ParadaDto;
import com.app.monopatin.models.entitys.Parada;
import com.app.monopatin.repositorys.ParadaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ParadaService {

    @Autowired
    private ParadaRepository paradaRepository;

    @Transactional
    public ParadaDto searchById(@PathVariable Integer id) {

        Optional<Parada> parada= paradaRepository.findById(id);

        if(parada.isPresent()) {
            return (this.convertDto(parada.get()));
        }
        else {
            return null;
        }
    }

    @Transactional
    public ParadaDto save(Parada parada) throws Exception {
        try{
            paradaRepository.save(parada);
            return this.convertDto(parada);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public ParadaDto deleteById(@PathVariable int id) {

        Optional<Parada> optionalMonopatin = paradaRepository.findById(id);

        if (optionalMonopatin.isPresent()) {
            Parada parada = optionalMonopatin.get();
            paradaRepository.deleteById(id);
            return this.convertDto(parada);
        } else {
            return null;
        }
    }

    @Transactional
    public List<ParadaDto> findAll(){
        return this.convertListDto(paradaRepository.findAll());

    }

    private List<ParadaDto> convertListDto(List<Parada> paradas) {

        List<ParadaDto> monopatinDtos = new ArrayList<>();
        for(Parada p : paradas){
            monopatinDtos.add(convertDto(p));
        }
        return monopatinDtos;
    }

    private ParadaDto convertDto(Parada parada) {

        return new ParadaDto(parada.getId(), parada.getCoordenadas(), parada.getMonopatines().size());
    }
}
