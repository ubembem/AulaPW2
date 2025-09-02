package br.edu.ifto.aula02.controller;

import br.edu.ifto.aula02.model.entity.Pessoa;
import br.edu.ifto.aula02.model.entity.Usuario;
import br.edu.ifto.aula02.model.entity.Venda;
import br.edu.ifto.aula02.repository.PessoaRepository;
import br.edu.ifto.aula02.repository.UsuarioRepository;
import br.edu.ifto.aula02.repository.VendaRepository;
import ch.qos.logback.classic.Logger;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Transactional
@Controller
@RequestMapping("venda")
public class VendaController {
    @Autowired
    VendaRepository vendaRepository;
    @Autowired
    PessoaRepository pessoaRepository;
    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping("/listar")
    public ModelAndView listar(ModelMap model) {
        model.addAttribute("vendas", vendaRepository.vendas());
        return new ModelAndView("/venda/list");
    }

    @GetMapping("/detalhes/{id}")
    public ModelAndView detalhes(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("venda", vendaRepository.venda(id));
        System.out.println(vendaRepository.venda(id).toString());
        return new ModelAndView("/venda/detail", model);
    }

    @GetMapping("/form")
    public String form(Venda venda){
        return "/venda/form";
    }

    @PostMapping("/save")
    public ModelAndView save(Venda venda){
        vendaRepository.save(venda);
        return new ModelAndView("redirect:/venda/listar");
    }

    @GetMapping("/remove/{id}")
    public ModelAndView remove(@PathVariable("id") Long id){
        vendaRepository.remove(id);
        return new ModelAndView("redirect:/venda/listar");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("venda", vendaRepository.venda(id));
        return new ModelAndView("/venda/form", model);
    }

    @PostMapping("/update")
    public ModelAndView update(Venda venda) {
        vendaRepository.update(venda);
        return new ModelAndView("redirect:/venda/listar");
    }

    @GetMapping("/consulta")
    public String consultaVendas(
            @RequestParam("dataInicial") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial,
            Model model) {

        // Converter LocalDate para LocalDateTime
        LocalDateTime inicio = dataInicial.atStartOfDay();

        List<Venda> vendas = vendaRepository.filtrarVendaPorData(inicio);

        model.addAttribute("vendas", vendas);
        model.addAttribute("dataInicial", dataInicial);
//        model.addAttribute("dataFinal", dataFinal);

        return "venda/list";
    }

    @GetMapping("/consultaClienteEData")
    public String consultaVendasClienteData(
            @RequestParam("clienteId") Long clienteId,
            @RequestParam(value = "dataInicial", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial,
            Model model) {

        // Consultar todos os clientes disponíveis
        List<Pessoa> clientes = pessoaRepository.pessoas();
        model.addAttribute("clientes", clientes);

        // Conversão para LocalDateTime, se necessário
        LocalDateTime inicio = (dataInicial != null) ? dataInicial.atStartOfDay() : null;

        // Consulta as vendas com o filtro
        List<Venda> vendas = vendaRepository.filtrarVendaPorClienteEPorData(clienteId, inicio);

        model.addAttribute("vendas", vendas);
        model.addAttribute("clienteId", clienteId);
        model.addAttribute("dataInicial", inicio);

        return "venda/list";
    }

    @GetMapping("/consultaPorClienteEData")
    public String form(Model model){
        List<Pessoa> clientes = pessoaRepository.pessoas();
        model.addAttribute("clientes", clientes);
        return "/venda/consultaClienteEData";
    }

    @GetMapping("/compras")
    public String minhasCompras(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            model.addAttribute("mensagemErro", "Usuário não autenticado.");
            return "redirect:/login";
        }
        // Pegando o e-nome do usuário autenticado
        String nomeUsuario = authentication.getName();
        Usuario usuario = usuarioRepository.buscaPorLogin(nomeUsuario);
        Pessoa clienteAtual = pessoaRepository.pessoa(usuario.getPessoa().getId());
        List<Venda> vendas = vendaRepository.filtrarVendaPorClienteLogado(clienteAtual.getId());
        model.addAttribute("vendas", vendas);
        return "venda/minhasCompras";
    }
}
