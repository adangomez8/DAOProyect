package TP3.APIRest.services;

import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ServiceBase<T> {
    /*public Optional<T> findById(Integer id);
    public List<T> findAll();

    public ResponseEntity<T> save(T element);
    public ResponseEntity<T> deleteById(Integer id);*/

    public T findById(Integer id) throws Exception;
    public List<T> findAll() throws Exception;
    public T save(T element) throws Exception;
    public T deleteById(Integer id) throws Exception;

}
