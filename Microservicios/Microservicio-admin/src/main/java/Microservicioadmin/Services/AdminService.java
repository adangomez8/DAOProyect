package Microservicioadmin.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import Microservicioadmin.Dto.DtoCuenta;
import Microservicioadmin.Dto.DtoMonopatin;
import Microservicioadmin.Dto.DtoParada;

@Service
public class AdminService {
	
	@Autowired
	RestTemplate template;
	
	public void saveMonopatin(@RequestBody DtoMonopatin monopatin) {
		
		template.postForObject("http://localhost:8081/monopatin", monopatin, DtoMonopatin.class);
	}
	
	public void saveParada(@RequestBody DtoParada parada) {
		
		template.postForObject("http://localhost:8081/parada", parada, DtoParada.class);
	}
	
	public void saveCuenta(@RequestBody DtoCuenta cuenta) {
		
		template.postForObject("http://localhost:8080/api/cuenta", cuenta, DtoCuenta.class);
	}
	
	public void deleteMonopatin(Integer id) {
		
		template.delete("http://localhost:8081/monopatin/"+id);
	}
	
	public void deleteParada(Integer id) {
		
		template.delete("http://localhost:8081/parada/"+id);
	}
	
	public void deleteCuenta(Integer id) {
		
		template.delete("http://localhost:8080/api/cuenta/"+id);
	}
	
	

}
