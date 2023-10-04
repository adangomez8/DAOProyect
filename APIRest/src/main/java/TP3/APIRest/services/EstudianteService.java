package TP3.APIRest.services;

import TP3.APIRest.repositories.EstudianteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import TP3.APIRest.entities.Estudiante;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstudianteService implements ServiceBase<Estudiante> {
    @Autowired
    private EstudianteRepository estudianteRepository;
   /* @Override
    @Transactional
    public Optional<Estudiante> findById(Integer id) {
        try {
            return estudianteRepository.findById(id);
        }catch (Exception e){
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public List<Estudiante> findAll() {
        try {
            return estudianteRepository.findAll();
        }catch (Exception e){
            return null;
        }
    }

    @Override
    @Transactional
    public ResponseEntity<Estudiante> save(Estudiante element) {
        try {
            estudianteRepository.save(element);
            return ResponseEntity.status(201).build();
        }catch (Exception e){
            return ResponseEntity.status(400).build();
        }
    }

    @Override
    @Transactional
    public ResponseEntity<Estudiante> deleteById(Integer id) {
        try {
            estudianteRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }*/

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
}
