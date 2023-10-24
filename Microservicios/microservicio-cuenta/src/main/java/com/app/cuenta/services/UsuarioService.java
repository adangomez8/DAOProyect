package com.app.cuenta.services;

import com.app.cuenta.dtos.UsuarioDto;
import com.app.cuenta.models.entitys.Usuario;
import com.app.cuenta.repositorys.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public UsuarioDto searchById(@PathVariable Integer id) {

        Optional<Usuario> usuario= usuarioRepository.findById(id);

        if(usuario.isPresent()) {
            return (this.convertDto(usuario.get()));
        }
        else {
            return null;
        }
    }

    @Transactional
    public UsuarioDto save(Usuario usuario) throws Exception {
        try{
            usuarioRepository.save(usuario);
            return this.convertDto(usuario);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public UsuarioDto deleteById(@PathVariable int id) {

        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);

        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            usuarioRepository.deleteById(id);
            return this.convertDto(usuario);
        } else {
            return null;
        }
    }

    @Transactional
    public List<UsuarioDto> findAll(){
        return this.convertListDto(usuarioRepository.findAll());

    }

    private List<UsuarioDto> convertListDto(List<Usuario> usuarios) {

        List<UsuarioDto> usuarioDtos = new ArrayList<>();
        for(Usuario u : usuarios){
            usuarioDtos.add(convertDto(u));
        }
        return usuarioDtos;
    }

    private UsuarioDto convertDto(Usuario usuario) {

        return new UsuarioDto(usuario.getNombreUsuario(), usuario.getNombre(), usuario.getApellido(), usuario.getNroCelular());
    }
}
