package Microservicioadmin.Security.Services;

import Microservicioadmin.Entities.SistemUser;
import Microservicioadmin.Repositories.SistemUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SistemUserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<SistemUser> sistemUser = Optional.ofNullable(repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado.")));

        return new User(sistemUser.get().getUsername(), sistemUser.get().getPassword(),new ArrayList<>());
    }
}
