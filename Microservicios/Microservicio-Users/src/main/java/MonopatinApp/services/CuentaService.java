package MonopatinApp.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import MonopatinApp.Dto.DtoCuenta;
import MonopatinApp.entities.Cuenta;
import MonopatinApp.repositories.CuentaRepository;
import jakarta.transaction.Transactional;

@Service
public class CuentaService {
	
	@Autowired
	CuentaRepository repository;
	
	@Transactional
	public List<DtoCuenta>getAll(){
		
		/*ArrayList<DtoCuenta>dto= new ArrayList<>();
		List<Cuenta>u= repository.findAll();
		
		for(int i=0;i<u.size();i++) {
			Cuenta c= u.get(i);
			DtoCuenta cDto= new DtoCuenta(c.getIdCuenta(),c.getSaldo(),c.getFechaAlta());
			dto.add(cDto);
		}
		return dto;*/
	    List<Cuenta> cuentas = repository.findAll();
	    
	    List<DtoCuenta> dtos = cuentas.stream()
	        .map(cuenta -> new DtoCuenta(cuenta.getIdCuenta(), cuenta.getSaldo(), cuenta.getFechaAlta()))
	        .collect(Collectors.toList());
	    
	    return dtos;
		
	}
	
	@Transactional
	public DtoCuenta getById(@PathVariable Integer id) {
		
		Optional<Cuenta>cu=repository.findById(id);
		
		if(cu.isPresent()) {
			Cuenta cuenta=cu.get();
			DtoCuenta dCuenta= new DtoCuenta(cuenta.getIdCuenta(),cuenta.getSaldo(),cuenta.getFechaAlta());
			return dCuenta;
		}
		else {
			return null;
		}
	}
	
	@Transactional
	public void create(@RequestBody Cuenta c) {
		
		if(!repository.existsById(c.getIdCuenta())) {	
			repository.save(c);		
		}
	}
	
	@Transactional
	public void update(@RequestBody Cuenta c) {
		
		if(repository.existsById(c.getIdCuenta())) {
			repository.save(c);
		}
	}
	
	@Transactional
	public void delete (@PathVariable Integer id) {
		Optional<Cuenta>cuenta=repository.findById(id);
		
		if(cuenta.isPresent()) {
			Cuenta cu= cuenta.get();
			repository.delete(cu);
		}
	}

}
