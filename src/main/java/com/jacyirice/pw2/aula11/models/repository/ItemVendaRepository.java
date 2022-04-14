/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jacyirice.pw2.aula11.models.repository;

import com.jacyirice.pw2.aula11.models.entity.ItemVenda;
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
public class ItemVendaRepository {

    @PersistenceContext
    private EntityManager em;

    public List<ItemVenda> itens() {
        Query query = em.createQuery("from ItemVenda");
        return query.getResultList();
    }

    public void save(ItemVenda itemVenda) {
        em.persist(itemVenda);
    }

    public ItemVenda itemVenda(Integer id) {
        return em.find(ItemVenda.class, id);
    }

    public void remove(Integer id) {
        ItemVenda itemVenda = em.find(ItemVenda.class, id);
        em.remove(itemVenda);
    }

    public void update(ItemVenda itemVenda) {
        em.merge(itemVenda);
    }

}
