package TP3.APIRest.services;

import TP3.APIRest.entities.Carrera;
import TP3.APIRest.entities.Estudiante;
import TP3.APIRest.repositories.CarreraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarreraService implements ServiceBase<Carrera>{

    @Autowired
    private CarreraRepository carreraRepository;


    @Override
    public Carrera findById(Integer id) throws Exception {
        try{
            Optional<Carrera> carreraBuscada = carreraRepository.findById(id);
            return carreraBuscada.get();
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<Carrera> findAll() throws Exception {
        return carreraRepository.findAll();
    }

    @Override
    public Carrera save(Carrera element) throws Exception {
        try {
            return carreraRepository.save(element);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Carrera deleteById(Integer id) throws Exception {
        try{
            Optional<Carrera> aux = carreraRepository.findById(id);
            if(aux.isPresent())
                carreraRepository.deleteById(id);
            return aux.get();
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public List<Carrera> getCarrerasOrderByInscriptos() throws Exception {

        try{
            List<Carrera> carrerasBuscadas = carreraRepository.getCarrerasOrderByCantInscriptos();
            return carrerasBuscadas;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
