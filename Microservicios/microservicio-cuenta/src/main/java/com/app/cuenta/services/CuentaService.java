package com.app.cuenta.services;

import com.app.cuenta.dtos.CuentaDto;
import com.app.cuenta.models.entitys.Cuenta;
import com.app.cuenta.repositorys.CuentaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CuentaService {


    @Autowired
    private CuentaRepository cuentaRepository;

    @Transactional
    public CuentaDto searchById(@PathVariable Integer id) {

        Optional<Cuenta> cuenta= cuentaRepository.findById(id);

        if(cuenta.isPresent()) {
            return (this.convertDto(cuenta.get()));
        }
        else {
            return null;
        }
    }

    @Transactional
    public CuentaDto save(Cuenta cuenta) throws Exception {
        try{
            cuentaRepository.save(cuenta);
            return this.convertDto(cuenta);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public CuentaDto deleteById(@PathVariable int id) {

        Optional<Cuenta> optionalCuenta = cuentaRepository.findById(id);

        if (optionalCuenta.isPresent()) {
            Cuenta cuenta = optionalCuenta.get();
            cuentaRepository.deleteById(id);
            return this.convertDto(cuenta);
        } else {
            return null;
        }
    }

    @Transactional
    public List<CuentaDto> findAll(){
        return this.convertListDto(cuentaRepository.findAll());

    }

    private List<CuentaDto> convertListDto(List<Cuenta> cuentas) {

        List<CuentaDto> cuentaDtos = new ArrayList<>();
        for(Cuenta c : cuentas){
            cuentaDtos.add(convertDto(c));
        }
        return cuentaDtos;
    }

    private CuentaDto convertDto(Cuenta cuenta) {

        return new CuentaDto(cuenta.getId(), cuenta.getCuentaMercadoPago(), cuenta.getSaldo(), cuenta.getUsuariosAsociados().size(), cuenta.getFechaDeAlta());
    }
}
