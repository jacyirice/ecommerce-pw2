/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jacyirice.pw2.ecommerce.models.repository;

import com.jacyirice.pw2.ecommerce.models.entity.Cidade;
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
public class CidadeRepository {
    
    @PersistenceContext
    private EntityManager em;

    public List<Cidade> cidades() {
        Query query = em.createQuery("from Cidade order by nome");
        return query.getResultList();
    }

    public Cidade cidade(Integer id) {
        return em.find(Cidade.class, id);
    }
}
