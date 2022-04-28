/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jacyirice.pw2.aula11.controller;

import com.jacyirice.pw2.aula11.models.entity.ClientePF;
import com.jacyirice.pw2.aula11.models.entity.ItemVenda;
import com.jacyirice.pw2.aula11.models.entity.Produto;
import com.jacyirice.pw2.aula11.models.entity.Venda;
import com.jacyirice.pw2.aula11.models.repository.ClientePFRepository;
import com.jacyirice.pw2.aula11.models.repository.ProdutoRepository;
import com.jacyirice.pw2.aula11.models.repository.VendaRepository;
import java.time.LocalDate;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author compo
 */
@Scope("request")
@Transactional
@Controller
@RequestMapping("venda")
public class VendaController {

    @Autowired
    VendaRepository repository;

    @Autowired
    ProdutoRepository repositoryP;

    @Autowired
    ClientePFRepository repositoryCientePF;

    @Autowired
    Venda venda;

    @GetMapping("/list")
    public ModelAndView listar(
            @RequestParam(value = "cliente_id", required = false) Integer cliente_id,
            @RequestParam(value = "dataStart", required = false) LocalDate dataStart,
            @RequestParam(value = "dataStop", required = false) LocalDate dataStop,
            ModelMap model
    ) {
        if (cliente_id != null && cliente_id > 0) {
            model.addAttribute("vendas", repository.findByClienteId(cliente_id));
        } else if (dataStart != null && dataStop != null) {
            model.addAttribute("vendas", repository.findByDate(dataStart, dataStop));
        } else {
            model.addAttribute("vendas", repository.vendas());
        }
        return new ModelAndView("/venda/list", model);
    }

    @PostMapping("/add-item")
    public ModelAndView addItem(@Valid ItemVenda itemVenda, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("errors", result.getAllErrors().get(0).getDefaultMessage());
            redirectAttributes.addFlashAttribute("produto_id_error", itemVenda.getProduto().getId());
            return new ModelAndView("redirect:/produto/list");
        }
        Produto produto = repositoryP.produto(itemVenda.getProduto().getId());

        itemVenda.setProduto(produto);
        itemVenda.setVenda(venda);
        venda.getItensVenda().add(itemVenda);

        redirectAttributes.addFlashAttribute("alert_message", produto.getDescricao() + " adicionado ao carrinho!");
        redirectAttributes.addFlashAttribute("alert_type", "success");
        return new ModelAndView("redirect:/produto/list");
    }

    @GetMapping("/del-item/{id}")
    public ModelAndView delItem(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        venda.getItensVenda().remove(id);
        redirectAttributes.addFlashAttribute("alert_type", "warning");
        redirectAttributes.addFlashAttribute("alert_message", "Produto removido do carrinho!");
        return new ModelAndView("redirect:/venda/carrinho");
    }

    @GetMapping("/carrinho")
    public ModelAndView form(Venda venda, ModelMap model) {
        model.addAttribute("clientesPF", repositoryCientePF.clientesPF());
        return new ModelAndView("/venda/cart", model);
    }

    @GetMapping("/save")
    public ModelAndView save(@RequestParam(value = "clienteId", required = false) Integer clienteId, RedirectAttributes redirectAttributes) {
        if (clienteId == null) {
            redirectAttributes.addFlashAttribute("error_client", "Selecione um cliente");
            return new ModelAndView("redirect:/venda/carrinho");
        }
        if (this.venda.getItensVenda().isEmpty()) {
            redirectAttributes.addFlashAttribute("error_cart", "Carrinho vazio");
            return new ModelAndView("redirect:/venda/carrinho");
        }

        ClientePF clientepf = repositoryCientePF.clientePF(clienteId);

        this.venda.setId(null);
        this.venda.setData(LocalDate.now());
        this.venda.setCliente(clientepf);
        repository.save(this.venda);
        this.venda.getItensVenda().clear();

        redirectAttributes.addFlashAttribute("alert_type", "success");
        redirectAttributes.addFlashAttribute("alert_message", "Venda armazenada com sucesso");
        return new ModelAndView("redirect:/venda/list");
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable("id") Integer id, ModelMap model) {
        model.addAttribute("venda", repository.venda(id));
        return new ModelAndView("/venda/detail", model);
    }

}
