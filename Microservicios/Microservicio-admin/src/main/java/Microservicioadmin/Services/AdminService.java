package Microservicioadmin.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import Microservicioadmin.Dto.DtoCuenta;
import Microservicioadmin.Dto.DtoMonopatin;
import Microservicioadmin.Dto.DtoParada;

import java.util.Date;
import java.util.List;

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
	
	public void updatePrecio(double precio,Date fecha) {
		template.put("http://localhost:8082/api/viaje/actualizar?precio="+precio+"&fecha="+fecha,null);
	}
	
	public double getRecaudacion() {
		ResponseEntity<Double>response= template.getForEntity("http://localhost:8082/api/viaje/recaudacion",Double.class);
		
		if(response.getStatusCode().is2xxSuccessful()) {
			Double recaudacion= response.getBody();
			return recaudacion;
		}
		else {
			return 0;
		}
	}
	
	public int getCantMonopatinDisponible() {
		ResponseEntity<Integer>response= template.getForEntity("http://localhost:8081/monopatin/disponibles", Integer.class);
		
		if(response.getStatusCode().is2xxSuccessful()) {
			Integer cantidad= response.getBody();
			return cantidad;
		}
		else {
			return -1;
		}
	}
	
	public int getCantMonopatinMantenimiento() {
		ResponseEntity<Integer>response= template.getForEntity("http://localhost:8081/monopatin/mantenimiento", Integer.class);
		
		if(response.getStatusCode().is2xxSuccessful()) {
			Integer cantidad= response.getBody();
			return cantidad;
		}
		else {
			return -1;
		}
	}

	public List<DtoMonopatin> getAllMonopatinesByYear(Integer year, Integer cantidad) {
		ResponseEntity<List> response = template.getForEntity("http://localhost:8082/api/viaje/year/2023?cantidad=2",List.class);
		if(response.getStatusCode().is4xxClientError()){

			return null;
		}else{
			return response.getBody();
		}
	}
}
