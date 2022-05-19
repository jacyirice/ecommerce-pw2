/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jacyirice.pw2.ecommerce.controller;

import com.jacyirice.pw2.ecommerce.models.entity.Produto;
import com.jacyirice.pw2.ecommerce.models.repository.ProdutoRepository;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author compo
 */
@Transactional
@Controller
@RequestMapping("produto")
public class ProdutoController {

    @Autowired
    ProdutoRepository repository;

    @GetMapping("/list")
    public ModelAndView listar(ModelMap model) {
        model.addAttribute("produtos", repository.produtos());

        return new ModelAndView("/produto/list", model);
    }

    @GetMapping("/form")
    public ModelAndView form(Produto produto) {
        return new ModelAndView("/produto/form");
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid Produto produto, BindingResult result) {
        if (result.hasErrors()) {
            return form(produto);
        }

        repository.save(produto);
        return new ModelAndView("redirect:/produto/list");
    }

    @GetMapping("/remove/{id}")
    public ModelAndView remove(@PathVariable("id") Integer id) {
        repository.remove(id);
        return new ModelAndView("redirect:/produto/list");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Integer id, ModelMap model) {
        model.addAttribute("produto", repository.produto(id));
        return new ModelAndView("/produto/form", model);
    }

    @PostMapping("/update")
    public ModelAndView update(@Valid Produto produto, BindingResult result) {
        if (result.hasErrors()) {
            return form(produto);
        }
        repository.update(produto);
        return new ModelAndView("redirect:/produto/list");
    }

}
