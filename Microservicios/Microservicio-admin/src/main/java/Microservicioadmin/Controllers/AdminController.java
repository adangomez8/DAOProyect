package Microservicioadmin.Controllers;

import Microservicioadmin.Security.Model.AuthResponse;
import Microservicioadmin.Security.Model.LoginRequest;
import Microservicioadmin.Services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Microservicioadmin.Dto.DtoCuenta;
import Microservicioadmin.Dto.DtoMonopatin;
import Microservicioadmin.Dto.DtoParada;
import Microservicioadmin.Services.AdminService;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

	@Autowired
	AdminService service;
	@Autowired
	private final AuthService authService;
	
	@GetMapping("/monopatin/reporte")
	public ResponseEntity<?>getEstadoMonopatin(){
		try {
			int disponible= service.getCantMonopatinDisponible();
			int mantenimiento=service.getCantMonopatinMantenimiento();
			return ResponseEntity.status(HttpStatus.OK).body("Hay "+disponible+" monopatines disponibles y "+mantenimiento+" en mantenimiento");
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error en realizar la consulta");
		}
	}
	
	@GetMapping("/viaje/recaudacion")
	public ResponseEntity<?> getRecaudacion(){
		try {
			double recaudacion= service.getRecaudacion();
			return ResponseEntity.status(HttpStatus.OK).body(recaudacion);		
			}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error en generar la consulta");
		}
	}
	
	@PostMapping("/monopatin")
	public ResponseEntity<?> saveMonopatin(@RequestBody DtoMonopatin monopatin) {
		System.out.println(monopatin);
		try {

			service.saveMonopatin(monopatin);
			return ResponseEntity.status(HttpStatus.CREATED).body("Monopatin agregado");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al crear un monopatin. Verifique que los campos sean validos");
		}
	}
	
	@PostMapping("/parada")
	public ResponseEntity<?> saveParada(@RequestBody DtoParada parada){
		
		try {

			service.saveParada(parada);
			return ResponseEntity.status(HttpStatus.CREATED).body("Parada agregada");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al crear una parada. Verifique que los campos sean validos");
		}		
	}
	
	@PostMapping("/cuenta")
	public ResponseEntity<?> saveCuenta(@RequestBody DtoCuenta cuenta){
		try {
			service.saveCuenta(cuenta);
			return ResponseEntity.status(HttpStatus.CREATED).body("Cuenta agregada");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al crear cuenta. Verifique que los campos sean validos");
		}
	}
	
	@DeleteMapping("/monopatin/{id}")
	public ResponseEntity<?> deleteMonopatin(@PathVariable Integer id){
		try {
			service.deleteMonopatin(id);
			return ResponseEntity.status(HttpStatus.OK).body("Monopatin eliminado");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error. El monopatin no existe");
		}
	}
	
	@DeleteMapping("/parada/{id}")
	public ResponseEntity<?> deleteParada(@PathVariable Integer id){
		try {
			service.deleteParada(id);
			return ResponseEntity.status(HttpStatus.OK).body("Parada eliminada");			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error. La parada no existe");
		}
	}
	
	@DeleteMapping("/cuenta/{id}")
	public ResponseEntity<?> deleteCuenta(@PathVariable Integer id){
		try {
			service.deleteCuenta(id);
			return ResponseEntity.status(HttpStatus.OK).body("Cuenta anulada");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error.La cuenta no existe");
		}
	}


	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest){
		return ResponseEntity.ok(authService.login(loginRequest));
	}
	@PostMapping("/register")
	public ResponseEntity<AuthResponse> register(@RequestBody LoginRequest loginRequest){
		return ResponseEntity.ok(authService.register(loginRequest));
	}

}
