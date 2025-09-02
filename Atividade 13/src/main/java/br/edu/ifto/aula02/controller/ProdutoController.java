package br.edu.ifto.aula02.controller;

import br.edu.ifto.aula02.model.entity.Pessoa;
import br.edu.ifto.aula02.model.entity.Produto;
import br.edu.ifto.aula02.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Transactional
@Controller
@RequestMapping("produto")
public class ProdutoController {
    @Autowired
    ProdutoRepository produtoRepository;

    @GetMapping("/listar")
    public ModelAndView listar(ModelMap model) {
        model.addAttribute("produtos", produtoRepository.produtos());
        return new ModelAndView("/produto/list");
    }

    @GetMapping("/form")
    public String form(Produto produto){
        return "/produto/form";
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid Produto produto, BindingResult result){
        if(result.hasErrors()){
            return new ModelAndView("/produto/form");
        }
        produtoRepository.save(produto);
        return new ModelAndView("redirect:/produto/listar");
    }

    @GetMapping("/remove/{id}")
    public ModelAndView remove(@PathVariable("id") Long id){
        produtoRepository.remove(id);
        return new ModelAndView("redirect:/produto/listar");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("produto", produtoRepository.produto(id));
        return new ModelAndView("/produto/form", model);
    }

    @PostMapping("/update")
    public ModelAndView update(@Valid Produto produto, BindingResult result) {
        if(result.hasErrors()){
            return new ModelAndView("/produto/form");
        }
        produtoRepository.update(produto);
        return new ModelAndView("redirect:/produto/listar");
    }

    @GetMapping("/consulta{nome}")
    public String consultarProduto(@RequestParam("descricao") String descricao, Model model) {
        List<Produto> produtos = produtoRepository.buscaPorNome(descricao);
        model.addAttribute("produtos", produtos.listIterator());
        model.addAttribute("descricaoBusca", descricao); // Para exibir o termo de busca na página
        if(!produtos.isEmpty()){
//            System.out.println(pessoas.get(0).getVendas());// pegar as vendas de uma pessoa
            System.out.println(produtos.get(0).getDescricao());
        }
        return "produto/list";
    }
}
