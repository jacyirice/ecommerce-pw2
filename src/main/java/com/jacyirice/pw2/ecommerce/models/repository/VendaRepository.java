/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jacyirice.pw2.ecommerce.models.repository;

import com.jacyirice.pw2.ecommerce.models.entity.Produto;
import com.jacyirice.pw2.ecommerce.models.entity.Venda;
import java.time.LocalDate;
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
public class VendaRepository {

    @PersistenceContext
    private EntityManager em;

    public List<Venda> vendas() {
        Query query = em.createQuery("from Venda");
        return query.getResultList();
    }

    public List<Venda> findByDate(LocalDate dataStart, LocalDate dataStop) {
        String hql = "from Venda where data BETWEEN :dataStart AND :dataStop";
        Query query = em.createQuery(hql, Venda.class);
        query.setParameter("dataStart", dataStart);
        query.setParameter("dataStop", dataStop);

        return query.getResultList();
    }

    public List<Venda> findByClienteId(Integer cliente_id) {
        String hql = "from Venda where id_cliente=:cliente_id";
        Query query = em.createQuery(hql, Venda.class);
        query.setParameter("cliente_id", cliente_id);
        return query.getResultList();
    }

    public void save(Venda venda) {
        em.persist(venda);
    }

    public Venda venda(Integer id) {
        return em.find(Venda.class, id);
    }

    public void remove(Integer id) {
        Venda venda = em.find(Venda.class, id);
        em.remove(venda);
    }

    public void update(Venda venda) {
        em.merge(venda);
    }
}
