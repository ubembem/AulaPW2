package br.ifto.edu.aula1708jpa.controller;

import br.ifto.edu.aula1708jpa.model.entity.PessoaJuridica;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("pessoajuridica")
public class PessoaJuridicaController {

    @GetMapping("/form")
    public ModelAndView form(PessoaJuridica pessoaJuridica, ModelMap model){
        model.addAttribute("pessoa", pessoaJuridica);
        return new ModelAndView("pessoa/form");
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute("pessoa") PessoaJuridica pessoaJuridica){
        //save pessoaFisica
        return new ModelAndView("redirect:/pessoa/list");
    }



}
