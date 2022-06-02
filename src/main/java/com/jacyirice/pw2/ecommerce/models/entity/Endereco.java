/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jacyirice.pw2.ecommerce.models.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 *
 * @author compo
 */
@Entity
@Table(name = "tb_endereco")
public class Endereco implements Serializable {

    @Id
    @GeneratedValue
    private int id;

    @NotBlank(message = "Digite o apelido")
    private String apelido;

    @NotBlank(message = "Digite o logradouro")
    private String logradouro;

    @NotBlank(message = "Digite o bairro")
    private String bairro;

    @NotBlank(message = "Digite o cep")
    private String cep;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @NotNull(message = "Selecione uma cidade")
    @ManyToOne
    @JoinColumn(name = "id_cidade")
    private Cidade cidade;

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public void cleanFields() {
        this.setApelido(null);
        this.setBairro(null);
        this.setCep(null);
        this.setCidade(null);
        this.setCliente(null);
        this.setLogradouro(null);
    }

    @Override
    public String toString() {
        return "Endereco{" + "id=" + id + ", apelido=" + apelido + ", logradouro=" + logradouro + ", bairro=" + bairro + ", cep=" + cep + ", cliente=" + cliente + ", cidade=" + cidade + '}';
    }
    
    
}
