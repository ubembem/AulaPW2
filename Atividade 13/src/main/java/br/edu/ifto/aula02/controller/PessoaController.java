package br.edu.ifto.aula02.controller;

import br.edu.ifto.aula02.model.entity.Pessoa;
import br.edu.ifto.aula02.model.entity.PessoaFisica;
import br.edu.ifto.aula02.repository.PessoaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Transactional
@Controller
@RequestMapping("pessoa")
public class PessoaController {
    @Autowired
    PessoaRepository pessoaRepository;

    @GetMapping("/listar")
    public ModelAndView listar(ModelMap model) {
        model.addAttribute("pessoas", pessoaRepository.pessoas());
        return new ModelAndView("/pessoa/list");
    }

    @GetMapping("/form")
    public String form(Pessoa pessoa){
        return "/pessoa/form";
    }


    @PostMapping("/save")
    public ModelAndView save(Pessoa pessoa){
        pessoaRepository.save(pessoa);
        return new ModelAndView("redirect:/pessoa/listar");
    }

    @GetMapping("/remove/{id}")
    public ModelAndView remove(@PathVariable("id") Long id){
        pessoaRepository.remove(id);
        return new ModelAndView("redirect:/pessoa/listar");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("pessoa", pessoaRepository.pessoa(id));
        return new ModelAndView("/pessoa/atualizar", model);
    }

    @PostMapping("/update")
    public ModelAndView update(Pessoa pessoa) {
        pessoaRepository.update(pessoa);
        return new ModelAndView("redirect:/pessoa/listar");
    }

    @GetMapping("/consulta{nome}")
    public String consultarPessoa(@RequestParam("nome") String nome, Model model) {
        List<Pessoa> pessoas = pessoaRepository.buscaPorNome(nome);
        model.addAttribute("pessoas", pessoas.listIterator());
        model.addAttribute("nomeBusca", nome); // Para exibir o termo de busca na página
        if(!pessoas.isEmpty()){
            System.out.println(pessoas.get(0).getEmail());
        }
        return "pessoa/list";
    }
}