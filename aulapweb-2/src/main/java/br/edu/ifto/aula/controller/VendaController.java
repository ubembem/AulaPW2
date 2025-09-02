package br.edu.ifto.aula.controller;

import br.edu.ifto.aula.model.entity.ItemVenda;
import br.edu.ifto.aula.model.entity.Produto;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Transactional
@Controller
@RequestMapping("/venda")
public class VendaController {

    @GetMapping("/list")
    public String list(Produto produto){
        //enviar a lista de produtos do banco para a view
        return "/venda/list";
    }

    @GetMapping("/carrinho")
    public String carrinho(){
        return "/venda/carrinho";
    }


    @PostMapping("/addItem")
    public String addItem(ItemVenda item){
        //venda.addItem(item);
        return "/venda/list";
    }

}

