/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jacyirice.pw2.ecommerce.models.repository;

import com.jacyirice.pw2.ecommerce.models.entity.Endereco;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author compo
 */
@Repository
public class EnderecoRepository {

    @PersistenceContext
    private EntityManager em;

    public List<Endereco> enderecos() {
        Query query = em.createQuery("from Endereco");
        return query.getResultList();
    }

    public void save(Endereco endereco) {
        em.persist(endereco);
    }

    public Endereco endereco(Integer id) {
        return em.find(Endereco.class, id);
    }

    public void remove(Integer id) {
        Endereco endereco = em.find(Endereco.class, id);
        em.remove(endereco);
    }

    public void update(Endereco endereco) {
        em.merge(endereco);
    }
}
