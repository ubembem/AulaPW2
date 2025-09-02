package br.ifto.edu.aula1708jpa.controller;

import br.ifto.edu.aula1708jpa.model.entity.PessoaFisica;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("pessoafisica")
public class PessoaFisicaController {

    @GetMapping("/form")
    public ModelAndView form(PessoaFisica pessoaFisica, ModelMap model){
        model.addAttribute("pessoa", pessoaFisica);
        return new ModelAndView("pessoa/form");
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute("pessoa") PessoaFisica pessoaFisica){
        //save pessoaFisica
        return new ModelAndView("redirect:/pessoa/list");
    }



}
