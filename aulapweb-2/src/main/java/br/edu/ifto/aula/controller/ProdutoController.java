package br.edu.ifto.aula.controller;

import br.edu.ifto.aula.model.entity.Produto;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Transactional
@Controller
@RequestMapping("/produto")
public class ProdutoController {

    @GetMapping("/list")
    public String list(Produto produto){
        //enviar a lista de produtos do banco para a view
        return "/produto/list";
    }



}

