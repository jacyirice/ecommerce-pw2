/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jacyirice.pw2.ecommerce.models.repository;

import com.jacyirice.pw2.ecommerce.models.entity.ClientePF;
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
public class ClientePFRepository {

    @PersistenceContext
    private EntityManager em;

    public List<ClientePF> clientesPF() {
        Query query = em.createQuery("from ClientePF");
        return query.getResultList();
    }

    public List<Venda> findByName(String nome) {
        String hql = "from ClientePF where nome LIKE :nome";
        Query query = em.createQuery(hql, ClientePF.class);
        query.setParameter("nome", "%"+nome+"%");
        return query.getResultList();
    }

    public List<ClientePF> findByCpf(String cpf) {
        String hql = "from ClientePF where cpf=:cpf";
        Query query = em.createQuery(hql, ClientePF.class);
        query.setParameter("cpf", cpf);
        return query.getResultList();
    }

    public void save(ClientePF clientePF) {
        em.persist(clientePF);
    }

    public ClientePF clientePF(Integer id) {
        return em.find(ClientePF.class, id);
    }

    public void remove(Integer id) {
        ClientePF clientePF = em.find(ClientePF.class, id);
        em.remove(clientePF);
    }

    public void update(ClientePF clientePF) {
        em.merge(clientePF);
    }
}
