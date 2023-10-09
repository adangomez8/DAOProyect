package TP3.API.Services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import DTOS.CarreraDTO;
import DTOS.ReporteCarreraDTO;
import TP3.APIRest.entities.Carrera;
import TP3.APIRest.repositories.CarreraRepository;
import jakarta.transaction.Transactional;

@Service
public class CarreraService {
	
	@Autowired
	private CarreraRepository repository;
	
	@Transactional
	public CarreraDTO searchById(@PathVariable Integer id) {
			
			Optional<Carrera> carrera= repository.findById(id);
			
			if(carrera.isPresent()) {
				return (this.transformOne(carrera.get()));
			}
			else {
				return null;
			}
		}
		@Transactional
		public Carrera searchByIdCARRERA(@PathVariable Integer id) {
				Optional<Carrera> carrera= repository.findById(id);

				if(carrera.isPresent()) {
					return carrera.get();
				}
				return null;
			}
	@Transactional
	public CarreraDTO save(Carrera entity) throws Exception {
		 try{
	            repository.save(entity);
	            return this.transformOne(entity);
	        }catch (Exception e){
	            throw new Exception(e.getMessage());
	        }
	}
	@Transactional
	public CarreraDTO deleteCarreraById(@PathVariable int id) {
	    
	        Optional<Carrera> optionalCarrera = repository.findById(id);

	        if (optionalCarrera.isPresent()) {
	            Carrera carrera = optionalCarrera.get();
	            repository.deleteById(id);
	            return this.transformOne(carrera);
	        } else {
	        	return null;
	        }

	}
	@Transactional
	public List<CarreraDTO> findAllWithStudents(){
		return this.transformDTO(repository.findCarrerasConEsutdiantesOrdenadas());
		
	}
	@Transactional
	public List<ReporteCarreraDTO> generateReport() {
        List<Object[]> reporte = repository.generateCarrerasReport();
        ArrayList<ReporteCarreraDTO> reporteDTO = new ArrayList<>();
        for (Object[] r : reporte) {
            if (r[1] == null) {
                r[1] = 0.0;
                r[2]=0;
                r[3]=0;
            }
            ReporteCarreraDTO dto = new ReporteCarreraDTO((String) r[0], ((Number) r[1]).intValue(), ((Number) r[2]).intValue(), ((Number) r[3]).intValue());
            reporteDTO.add(dto);
        }
        return reporteDTO;
    }
	@Transactional
	public List<CarreraDTO> findAll(){
		return this.transformDTO(repository.findAll());
		
	}
	
	private List<CarreraDTO> transformDTO(List<Carrera> carreras){
		List<CarreraDTO>carrerasDTO=new ArrayList();
		for(Carrera c:carreras) {
			carrerasDTO.add(this.transformOne(c));
		}
		return carrerasDTO;
	}
	
	private CarreraDTO transformOne(Carrera c) {
		return new CarreraDTO(c.getId(),c.getNombre(),c.getDuracion(),c.getEstudiantes().size());
	}
}
