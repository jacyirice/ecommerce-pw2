/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jacyirice.pw2.aula11.controller;

import com.jacyirice.pw2.aula11.models.entity.ClientePF;
import com.jacyirice.pw2.aula11.models.repository.ClientePFRepository;
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
@RequestMapping("clientePF")
public class ClientePFController {

    @Autowired
    ClientePFRepository repository;

    @GetMapping("/list")
    public ModelAndView listar(ModelMap model) {
        model.addAttribute("clientesPF", repository.clientesPF());

        return new ModelAndView("/clientePF/list", model);
    }

    @GetMapping("/form")
    public ModelAndView form(ClientePF clientePF) {
        return new ModelAndView("/clientePF/form");
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid ClientePF clientePF, BindingResult result) {
        if (result.hasErrors()) {
            return form(clientePF);
        }
        repository.save(clientePF);
        return new ModelAndView("redirect:/clientePF/list");
    }

    @GetMapping("/remove/{id}")
    public ModelAndView remove(@PathVariable("id") Integer id) {
        repository.remove(id);
        return new ModelAndView("redirect:/clientePF/list");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Integer id, ModelMap model) {
        model.addAttribute("clientePF", repository.clientePF(id));
        return new ModelAndView("/clientePF/form", model);
    }

    @PostMapping("/update")
    public ModelAndView update(@Valid ClientePF clientePF, BindingResult result) {
        if (result.hasErrors()) {
            return form(clientePF);
        }
        repository.update(clientePF);
        return new ModelAndView("redirect:/clientePF/list");
    }
}
