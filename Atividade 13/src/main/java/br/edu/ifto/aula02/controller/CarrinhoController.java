package br.edu.ifto.aula02.controller;

import br.edu.ifto.aula02.model.entity.*;
import br.edu.ifto.aula02.repository.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/carrinho")
@SessionAttributes("carrinho")
@Transactional
public class CarrinhoController {
    @Autowired
    VendaRepository vendaRepository;
    @Autowired
    ProdutoRepository produtoRepository;
    @Autowired
    PessoaRepository pessoaRepository;
    @Autowired
    ItemVendaRepository itemVenda ;
    @Autowired
    ItemVendaRepository itemVendaRepository;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    EnderecoEntregaRepository enderecoEntregaRepository;

    @ModelAttribute("carrinho")
    public ArrayList<ItemCarrinho> carrinho() {
        return new ArrayList<>();
    }

    @PostMapping("/adicionar")
    public String adicionarAoCarrinho(@RequestParam Long idProduto,
                                      @RequestParam(required = false, defaultValue = "1") int quantidade,
                                      @ModelAttribute("carrinho") ArrayList<ItemCarrinho> carrinho) {
        Produto produto = produtoRepository.produto(idProduto);

        Optional<ItemCarrinho> itemExistente = carrinho.stream()
                .filter(item -> item.getProduto().getId().equals(idProduto))
                .findFirst();

        if (itemExistente.isPresent()) {
            // Atualiza a quantidade do item
            itemExistente.get().setQuantidade(itemExistente.get().getQuantidade() + quantidade);
        } else {
            carrinho.add(new ItemCarrinho(produto, quantidade));
        }
        return "redirect:/produto/listar";
    }

    @PostMapping("/atualizar")
    public String atualizarCarrinho(@RequestParam Long idProduto,
                                      @RequestParam int quantidade,
                                      @ModelAttribute("carrinho") ArrayList<ItemCarrinho> carrinho, Model model) {
        Produto produto = produtoRepository.produto(idProduto);

        Optional<ItemCarrinho> itemExistente = carrinho.stream()
                .filter(item -> item.getProduto().getId().equals(idProduto))
                .findFirst();

        // Atualiza a quantidade do item
        itemExistente.get().setQuantidade(quantidade);
        // Passando a enum diretamente para a view
        FormaPagamentoEnum[] formasPagamento = FormaPagamentoEnum.values();
        model.addAttribute("formasPagamento", formasPagamento);
        return "carrinho/list";
    }

    @GetMapping("/listar")
    public String visualizarCarrinho(@ModelAttribute("carrinho") ArrayList<ItemCarrinho> carrinho, Model model) {
        BigDecimal totalCompra = carrinho.stream()
                .map(item -> item.getProduto()
                        .getValor()
                        .multiply(BigDecimal.valueOf(item.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        model.addAttribute("totalCompra", totalCompra);
        // Passando a enum diretamente para a view
        FormaPagamentoEnum[] formasPagamento = FormaPagamentoEnum.values();
        model.addAttribute("formasPagamento", formasPagamento);
        return "carrinho/list";
    }

    @PostMapping("/remover")
    public String removerDoCarrinho(@RequestParam Long idProduto, @ModelAttribute("carrinho") ArrayList<ItemCarrinho> carrinho) {
        carrinho.removeIf(item -> item.getProduto().getId().equals(idProduto));
        //carrinho.remove(index);
        return "redirect:/carrinho/listar";
    }

    @PostMapping("/finalizar")
    public ModelAndView finalizarCompra(@ModelAttribute("carrinho") ArrayList<ItemCarrinho> carrinho,
                                  @RequestParam String logradouro,
                                  @RequestParam String numero,
                                  @RequestParam String bairro,
                                  @RequestParam String cidade,
                                  @RequestParam String estado,
                                  @RequestParam String cep,
                                  @RequestParam String formaPagamento,
                                  Model model, RedirectAttributes redirectAttributes, BindingResult bindingResult) {
        if (carrinho.isEmpty()) {
            redirectAttributes.addFlashAttribute("mensagemErro", "O carrinho está vazio. Adicione itens antes de finalizar a compra.");
            return new ModelAndView("redirect:/carrinho/listar");
        }
        if (bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("/carrinho/list");
            modelAndView.addObject("carrinho", carrinho);
            return modelAndView;
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            model.addAttribute("mensagemErro", "Usuário não autenticado.");
            return new ModelAndView("/login");
        }
        // Pegando o e-nome do usuário autenticado
        String nomeUsuario = authentication.getName();
        Usuario usuario = usuarioRepository.buscaPorLogin(nomeUsuario);
        Pessoa clienteAtual = usuario.getPessoa();

        // Criar um novo endereço de entrega com os dados informados
        EnderecoEntrega enderecoEntrega = new EnderecoEntrega();
        enderecoEntrega.setLogradouro(logradouro);
        enderecoEntrega.setNumero(numero);
        enderecoEntrega.setBairro(bairro);
        enderecoEntrega.setCidade(cidade);
        enderecoEntrega.setEstado(estado);
        enderecoEntrega.setCep(cep);

        FormaPagamentoEnum formaPagamentoEnum = FormaPagamentoEnum.valueOf(formaPagamento);


        Venda venda = new Venda();
        venda.setDataVenda(LocalDateTime.now());
        enderecoEntrega.setVenda(venda);
        enderecoEntregaRepository.save(enderecoEntrega);

        for (ItemCarrinho item : carrinho) {
            ItemVenda itemVenda = new ItemVenda();
            itemVenda.setProduto(item.getProduto());
            itemVenda.setQuantidade(item.getQuantidade());
            itemVenda.setVenda(venda);
            itemVendaRepository.save(itemVenda);

            venda.getItensVenda().add(itemVenda);
        }

        venda.setPessoa(clienteAtual);
        venda.setEnderecoEntrega(enderecoEntrega);
        venda.setFormaPagamentoEnum(formaPagamentoEnum);
        vendaRepository.save(venda);
        carrinho.clear();
        return new ModelAndView("redirect:/produto/listar");
    }
}
