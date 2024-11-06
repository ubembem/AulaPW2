package br.edu.ifto.aula02.controller;

import br.edu.ifto.aula02.model.entity.Venda;
import br.edu.ifto.aula02.repository.VendaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Transactional
@Controller
@RequestMapping("venda")
public class VendaController {
    @Autowired
    VendaRepository vendaRepository;

    @GetMapping("/listar")
    public ModelAndView listar(ModelMap model) {
        model.addAttribute("vendas", vendaRepository.vendas());
        return new ModelAndView("/venda/list");
    }

    @GetMapping("/detalhes/{id}")
    public ModelAndView detalhes(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("venda", vendaRepository.venda(id));
        System.out.println("Passou da consulta " + id);
        return new ModelAndView("/venda/detail", model);
    }


    @GetMapping("/form")
    public String form(Venda venda){
        return "/venda/form";
    }

    @PostMapping("/save")
    public ModelAndView save(Venda venda){
        vendaRepository.save(venda);
        return new ModelAndView("redirect:/venda/listar");
    }

    /**
     * @param id
     * @return
     * @PathVariable é utilizado quando o valor da variável é passada diretamente na URL
     */
    @GetMapping("/remove/{id}")
    public ModelAndView remove(@PathVariable("id") Long id){
        vendaRepository.remove(id);
        return new ModelAndView("redirect:/venda/listar");
    }

    /**
     * @param id
     * @return
     * @PathVariable é utilizado quando o valor da variável é passada diretamente na URL
     */
    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("venda", vendaRepository.venda(id));
        return new ModelAndView("/venda/form", model);
    }

    @PostMapping("/update")
    public ModelAndView update(Venda venda) {
        vendaRepository.update(venda);
        return new ModelAndView("redirect:/venda/listar");
    }

}
