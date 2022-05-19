/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jacyirice.pw2.ecommerce.models.repository;

import com.jacyirice.pw2.ecommerce.models.entity.Produto;
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
public class ProdutoRepository {

    @PersistenceContext
    private EntityManager em;

    public List<Produto> produtos() {
        Query query = em.createQuery("from Produto");
        return query.getResultList();
    }

    public void save(Produto produto) {
        em.persist(produto);
    }

    public Produto produto(Integer id) {
        return em.find(Produto.class, id);
    }

    public void remove(Integer id) {
        Produto produto = em.find(Produto.class, id);
        em.remove(produto);
    }

    public void update(Produto produto) {
        em.merge(produto);
    }

}
