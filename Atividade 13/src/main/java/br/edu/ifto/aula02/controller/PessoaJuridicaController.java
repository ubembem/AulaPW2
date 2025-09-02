package br.edu.ifto.aula02.controller;

import br.edu.ifto.aula02.model.entity.PessoaJuridica;
import br.edu.ifto.aula02.model.entity.Role;
import br.edu.ifto.aula02.model.entity.Usuario;
import br.edu.ifto.aula02.repository.PessoaJuridicaRepository;
import br.edu.ifto.aula02.repository.RoleRepository;
import br.edu.ifto.aula02.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("pessoajuridica")
public class PessoaJuridicaController {

    @Autowired
    PessoaJuridicaRepository pessoaJuridicaRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    RoleRepository roleRepository;

    @GetMapping("/listar")
    public ModelAndView listar(ModelMap model) {
        model.addAttribute("pessoajuridica", pessoaJuridicaRepository.pessoasJuridica());
        return new ModelAndView("/pessoajuridica/list");
    }

    @GetMapping("/form")
    public String form(PessoaJuridica pessoajuridica,  Model model){
        if (pessoajuridica.getUsuario() == null) {
            pessoajuridica.setUsuario(new Usuario()); // Evita NullPointerException no formulário
        }
        model.addAttribute("isFind", true);
        model.addAttribute("pessoajuridica", pessoajuridica);
        return "/pessoa/form";
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid @ModelAttribute("pessoaJuridica") PessoaJuridica pessoaJuridica,
                             BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("/pessoa/form");
            modelAndView.addObject("pessoaJuridica", pessoaJuridica);
            return modelAndView;
        }

        String password = new BCryptPasswordEncoder().encode(pessoaJuridica.getUsuario().getPassword());
        pessoaJuridica.getUsuario().setPassword(password);

        Role userRole = roleRepository.findByNome("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Role USER não encontrada!"));
        pessoaJuridica.getUsuario().getRoles().add(userRole);

        if (pessoaJuridica.getUsuario() != null && pessoaJuridica.getUsuario().getId() == null) {
            // Salvar primeiro o usuário se ainda não estiver salvo
            pessoaJuridica.setUsuario(usuarioRepository.save(pessoaJuridica.getUsuario()));
        }
        pessoaJuridicaRepository.save(pessoaJuridica);
        pessoaJuridica.getUsuario().setPessoa(pessoaJuridicaRepository.pessoaJuridica(pessoaJuridica.getId()));
        return new ModelAndView("redirect:/login");
    }


    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("pessoaJuridica", pessoaJuridicaRepository.pessoaJuridica(id));
        return new ModelAndView("/pessoa/form", model);
    }

    @PostMapping("/update")
    public ModelAndView update(@Valid PessoaJuridica pessoajuridica, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            ModelAndView modelAndView = new ModelAndView("/pessoa/form");
            modelAndView.addObject("pessoaJuridica", pessoajuridica);
            return new ModelAndView("/pessoa/form");
        }
        pessoaJuridicaRepository.update(pessoajuridica);
        return new ModelAndView("redirect:/pessoa/listar");
    }

}
