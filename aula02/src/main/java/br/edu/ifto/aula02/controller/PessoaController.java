package br.edu.ifto.aula02.controller;

import br.edu.ifto.aula02.model.dao.PessoaDAO;
import br.edu.ifto.aula02.model.entity.Pessoa;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("pessoa")
public class PessoaController {

    PessoaDAO dao;

    public PessoaController(){
        dao = new PessoaDAO();
    }

    @GetMapping("/list")
    public ModelAndView listar(ModelMap model) {
        model.addAttribute("pessoas", dao.buscarPessoas());
        return new ModelAndView("/pessoa/list");
    }

    /**
     * @param pessoa necessário devido utilizar no form.html o th:object que faz referência ao objeto esperado no controller.
     * @return
     */
    @GetMapping("/form")
    public String form(Pessoa pessoa){
        return "/pessoa/form";
    }

    @PostMapping("/save")
    public ModelAndView save(Pessoa pessoa){
        dao.save(pessoa);
        return new ModelAndView("redirect:/pessoa/list");
    }

    /**
     * @param id
     * @return
     * @PathVariable é utilizado quando o valor da variável é passada diretamente na URL
     */
    @GetMapping("/remove/{id}")
    public ModelAndView remove(@PathVariable("id") Long id){
        dao.remove(id);
        return new ModelAndView("redirect:/pessoa/list");
    }

    /**
     * @param id
     * @return
     * @PathVariable é utilizado quando o valor da variável é passada diretamente na URL
     */
    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("pessoa", dao.buscarPessoa(id));
        return new ModelAndView("/pessoa/form", model);
    }

    @PostMapping("/update")
    public ModelAndView update(Pessoa pessoa) {
        dao.update(pessoa);
        return new ModelAndView("redirect:/pessoa/list");
    }


}
