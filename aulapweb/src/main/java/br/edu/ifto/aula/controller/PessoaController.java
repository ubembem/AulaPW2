package br.edu.ifto.aula.controller;

import br.edu.ifto.aula.model.entity.PessoaFisica;
import br.edu.ifto.aula.model.repository.PessoaFisicaRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Transactional
@Scope("request")
@Controller
@RequestMapping("pessoa")
public class PessoaController {

    @Autowired
    PessoaFisicaRepository repository;

    @GetMapping("/form")
    public String form(PessoaFisica pessoa){
        return "/pessoa/form";
    }

    @PostMapping("/save")
    public ModelAndView save(PessoaFisica pessoaFisica, HttpSession session){
        session.invalidate();
        return new ModelAndView("redirect:/pessoa/list");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("pessoa", repository.pessoa(id));
        return new ModelAndView("/pessoa/form");
    }

    @GetMapping("/list")
    public ModelAndView listar(ModelMap model) {
        model.addAttribute("msg","Lista de Pessoas");
        model.addAttribute("pessoas", repository.pessoas());
        return new ModelAndView("/pessoa/list"); //caminho para a view
    }

}
