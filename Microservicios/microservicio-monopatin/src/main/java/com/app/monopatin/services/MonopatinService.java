package com.app.monopatin.services;

import com.app.monopatin.dtos.MonopatinDto;
import com.app.monopatin.models.entitys.Monopatin;
import com.app.monopatin.repositorys.MonopatinRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MonopatinService {

    @Autowired
    private MonopatinRepository monopatinRepository;

    @Transactional
    public MonopatinDto searchById(@PathVariable Integer id) {

        Optional<Monopatin> monopatin= monopatinRepository.findById(id);

        if(monopatin.isPresent()) {
            return (this.convertDto(monopatin.get()));
        }
        else {
            return null;
        }
    }

    @Transactional
    public MonopatinDto save(Monopatin monopatin) throws Exception {
        try{
            monopatinRepository.save(monopatin);
            return this.convertDto(monopatin);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public MonopatinDto deleteById(@PathVariable int id) {

        Optional<Monopatin> optionalMonopatin = monopatinRepository.findById(id);

        if (optionalMonopatin.isPresent()) {
            Monopatin monopatin = optionalMonopatin.get();
            monopatinRepository.deleteById(id);
            return this.convertDto(monopatin);
        } else {
            return null;
        }
    }

    @Transactional
    public List<MonopatinDto> findAll(){
        return this.convertListDto(monopatinRepository.findAll());

    }

    private List<MonopatinDto> convertListDto(List<Monopatin> monopatins) {

        List<MonopatinDto> monopatinDtos = new ArrayList<>();
        for(Monopatin m : monopatins){
            monopatinDtos.add(convertDto(m));
        }
        return monopatinDtos;
    }

    private MonopatinDto convertDto(Monopatin monopatin) {

        return new MonopatinDto(monopatin.getId(), monopatin.getEstado(), monopatin.getKilometros(), monopatin.isEnMantenimiento());
    }
}
