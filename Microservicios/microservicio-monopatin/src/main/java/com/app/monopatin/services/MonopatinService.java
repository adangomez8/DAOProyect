package com.app.monopatin.services;

import com.app.monopatin.dtos.MonopatinDto;
import com.app.monopatin.entitys.Monopatin;
import com.app.monopatin.repositorys.MonopatinRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MonopatinService {

    @Autowired
    private MonopatinRepository repository;

    @Transactional
    public MonopatinDto getById(@PathVariable Integer id) {

        Optional<Monopatin> monopatin= repository.findById(id);

        if(monopatin.isPresent()) {
            return (this.convertDto(monopatin.get()));
        }
        else {
            return null;
        }
    }

    @Transactional
    public MonopatinDto create(Monopatin monopatin) throws Exception {
        try{
            repository.save(monopatin);
            return this.convertDto(monopatin);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public MonopatinDto delete(@PathVariable int id) {

        Optional<Monopatin> optionalMonopatin = repository.findById(id);

        if (optionalMonopatin.isPresent()) {
            Monopatin monopatin = optionalMonopatin.get();
            repository.deleteById(id);
            return this.convertDto(monopatin);
        } else {
            return null;
        }
    }

    @Transactional
    public List<MonopatinDto>getAll(){
        List<Monopatin> monopatines = repository.findAll();

        List<MonopatinDto> dtos = monopatines.stream()
                .map(monopatin -> new MonopatinDto(monopatin.getId(), monopatin.getEstado(), monopatin.getKilometros() ,monopatin.getLatitud(), monopatin.getLongitud(), monopatin.getTiempoEnUso(), monopatin.getTiempoEnPausa()))
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

    private MonopatinDto convertDto(Monopatin monopatin) {

        return new MonopatinDto(monopatin.getId(), monopatin.getEstado(), monopatin.getKilometros() ,monopatin.getLatitud(), monopatin.getLongitud(), monopatin.getTiempoEnUso(), monopatin.getTiempoEnPausa());
    }
}
