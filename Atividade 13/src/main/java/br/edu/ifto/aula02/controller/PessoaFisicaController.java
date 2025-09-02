package br.edu.ifto.aula02.controller;

import br.edu.ifto.aula02.model.entity.PessoaFisica;
import br.edu.ifto.aula02.model.entity.PessoaJuridica;
import br.edu.ifto.aula02.model.entity.Role;
import br.edu.ifto.aula02.model.entity.Usuario;
import br.edu.ifto.aula02.repository.PessoaFisicaRepository;
import br.edu.ifto.aula02.repository.RoleRepository;
import br.edu.ifto.aula02.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Transactional
@Controller
@RequestMapping("pessoafisica")
public class PessoaFisicaController {
    @Autowired
    PessoaFisicaRepository pessoaFisicaRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    RoleRepository roleRepository;

    @GetMapping("/listar")
    public ModelAndView listar(ModelMap model) {
        model.addAttribute("pessoafisica", pessoaFisicaRepository.pessoasFisica());
        return new ModelAndView("/pessoafisica/list");
    }

    //Pessoa fisica necessário devido utilizar no form o th:object que faz referência ao objeto esperado no controller.
    @GetMapping("/form")
    public String form(PessoaFisica pessoaFisica, Model model){
        if (pessoaFisica.getUsuario() == null) {
            pessoaFisica.setUsuario(new Usuario()); // Evita NullPointerException no formulário
        }
        model.addAttribute("isFind", true);
        model.addAttribute("pessoaFisica", pessoaFisica);
        return "/pessoa/form";
    }


@PostMapping("/save")
public ModelAndView save(@Valid @ModelAttribute("pessoaFisica") PessoaFisica pessoaFisica,
                         BindingResult bindingResult, Model model) {
    if (bindingResult.hasErrors()) {
        ModelAndView modelAndView = new ModelAndView("/pessoa/form");
        modelAndView.addObject("pessoaFisica", pessoaFisica);
        return modelAndView;
    }

    String password = new BCryptPasswordEncoder().encode(pessoaFisica.getUsuario().getPassword());
    pessoaFisica.getUsuario().setPassword(password);

    Role userRole = roleRepository.findByNome("ROLE_USER")
            .orElseThrow(() -> new RuntimeException("Role USER não encontrada!"));
    pessoaFisica.getUsuario().getRoles().add(userRole);
    if (pessoaFisica.getUsuario() != null && pessoaFisica.getUsuario().getId() == null) {
        // Salvar primeiro o usuário se ainda não estiver salvo
        pessoaFisica.setUsuario(usuarioRepository.save(pessoaFisica.getUsuario()));
    }
    pessoaFisicaRepository.save(pessoaFisica);
    pessoaFisica.getUsuario().setPessoa(pessoaFisicaRepository.pessoaFisica(pessoaFisica.getId()));
    return new ModelAndView("redirect:/login");
}

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap model) {
        PessoaFisica pessoaFisica = pessoaFisicaRepository.pessoaFisica(id);
        if (pessoaFisica == null) {
            return "redirect:/pessoafisica/listar";
        }
        model.addAttribute("pessoaFisica", pessoaFisica);
        return "pessoa/form";
    }

    @PostMapping("/update")
    public ModelAndView update(@Valid PessoaFisica pessoaFisica, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            ModelAndView modelAndView = new ModelAndView("/pessoa/form");
            modelAndView.addObject("pessoaFisica", pessoaFisica);
            return modelAndView;
        }
        pessoaFisicaRepository.update(pessoaFisica);
        return new ModelAndView("redirect:/pessoa/listar");
    }
}