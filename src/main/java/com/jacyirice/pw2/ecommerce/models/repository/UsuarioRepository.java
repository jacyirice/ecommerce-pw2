/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jacyirice.pw2.ecommerce.models.repository;

import com.jacyirice.pw2.ecommerce.models.entity.ClientePF;
import com.jacyirice.pw2.ecommerce.models.entity.Usuario;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author compo
 */
@Repository
public class UsuarioRepository {
    
    
    @PersistenceContext
    private EntityManager em;
    
    public Usuario usuario(String login){
        String hql = "from Usuario where login=:login";
        Query query = em.createQuery(hql, Usuario.class);
        query.setParameter("login", login);
        return (Usuario) query.getSingleResult();
    }
    
    public void save(Usuario usuario) {
        em.persist(usuario);
    }
    
    
}
