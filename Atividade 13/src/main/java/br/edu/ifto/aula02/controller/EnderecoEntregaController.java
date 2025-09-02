package br.edu.ifto.aula02.controller;

import br.edu.ifto.aula02.model.entity.EnderecoEntrega;
import br.edu.ifto.aula02.model.entity.Pessoa;
import br.edu.ifto.aula02.repository.EnderecoEntregaRepository;
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
@RequestMapping("enderecoEntrega")
public class EnderecoEntregaController {
    @Autowired
    EnderecoEntregaRepository enderecoEntregaRepository;

    @GetMapping("/listar")
    public ModelAndView listar(ModelMap model) {
        model.addAttribute("enderecosEntrega", enderecoEntregaRepository.enderecoEntregas());
        return new ModelAndView("/enderecoEntrega/list");
    }

    @GetMapping("/form")
    public String form(EnderecoEntregaRepository enderecoEntregaRepository){
        return "/enderecoEntrega/form";
    }


    @PostMapping("/save")
    public ModelAndView save(EnderecoEntrega enderecoEntrega){
        enderecoEntregaRepository.save(enderecoEntrega);
        return new ModelAndView("redirect:/enderecoEntrega/listar");
    }

    @GetMapping("/remove/{id}")
    public ModelAndView remove(@PathVariable("id") Long id){
        enderecoEntregaRepository.remove(id);
        return new ModelAndView("redirect:/enderecoEntrega/listar");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("enderecoEntrega", enderecoEntregaRepository.enderecoEntrega(id));
        return new ModelAndView("/enderecoEntrega/atualizar", model);
    }

    @PostMapping("/update")
    public ModelAndView update(EnderecoEntrega enderecoEntrega) {
        enderecoEntregaRepository.update(enderecoEntrega);
        return new ModelAndView("redirect:/enderecoEntrega/listar");
    }

    @GetMapping("/consulta{nome}")
    public String consultarPessoa(@RequestParam("logradouro") String logradouro, Model model) {
        List<EnderecoEntrega> enderecoEntregas = enderecoEntregaRepository.buscaPorNome(logradouro);
        model.addAttribute("enderecos", enderecoEntregas.listIterator());
        model.addAttribute("nomeBusca", logradouro); // Para exibir o termo de busca na página
        if(!enderecoEntregas.isEmpty()){
            System.out.println(enderecoEntregas.get(0).getLogradouro());
        }
        return "enderecoEntrega/list";
    }
}