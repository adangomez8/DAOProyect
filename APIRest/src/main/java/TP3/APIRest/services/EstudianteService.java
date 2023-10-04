package TP3.APIRest.services;

import TP3.APIRest.repositories.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import TP3.APIRest.entities.Estudiante;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstudianteService implements ServiceBase<Estudiante> {
    @Autowired
    private EstudianteRepository estudianteRepository;
    @Override
    public Estudiante findById(Integer id) throws Exception{
        try{
            Optional<Estudiante> estudianteBuscado = estudianteRepository.findById(id);
            return estudianteBuscado.get();
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<Estudiante> findAll() throws Exception{
        return estudianteRepository.findAll();
    }

    @Override
    public Estudiante save(Estudiante element) throws Exception {
        try {
            return estudianteRepository.save(element);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Estudiante deleteById(Integer id) throws Exception{
        try{
            Optional<Estudiante> aux = estudianteRepository.findById(id);
            if(aux.isPresent())
                estudianteRepository.deleteById(id);
            return aux.get();
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public Estudiante getEstudianteByLibreta(int nroLibreta) throws Exception {

        try{
        Optional<Estudiante> estudianteBuscado = Optional.ofNullable(estudianteRepository.getEstudianteByLibreta(nroLibreta));
        return estudianteBuscado.get();
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public List<Estudiante> getEstudianteByGenero(String genero) throws Exception {

        try{
            List<Estudiante> estudianteBuscado = estudianteRepository.getEstudianteByGenero(genero);
            return estudianteBuscado;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
