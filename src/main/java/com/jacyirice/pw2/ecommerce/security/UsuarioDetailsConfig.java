/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jacyirice.pw2.ecommerce.security;

import com.jacyirice.pw2.ecommerce.models.entity.Usuario;
import com.jacyirice.pw2.ecommerce.models.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

/**
 *
 * @author compo
 */
@Repository
public class UsuarioDetailsConfig implements UserDetailsService {
    
    @Autowired
    UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Usuario usuario = repository.usuario(login);
        if (usuario == null) {
            throw new UsernameNotFoundException(login);
        }
        return usuario;
    }

}
