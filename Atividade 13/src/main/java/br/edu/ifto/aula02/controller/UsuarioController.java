//package br.edu.ifto.aula02.controller;
//
//import br.edu.ifto.aula02.model.entity.Produto;
//import br.edu.ifto.aula02.model.entity.Usuario;
//import br.edu.ifto.aula02.repository.UsuarioRepository;
//import jakarta.transaction.Transactional;
//import jakarta.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.ui.ModelMap;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.ModelAndView;
//
//@Transactional
//@Controller
//@RequestMapping("usuario")
//public class UsuarioController {
//    private final UsuarioRepository usuarioRepository;
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    public UsuarioController(UsuarioRepository usuarioRepository) {
//        this.usuarioRepository = usuarioRepository;
//    }
//    @GetMapping("/listar")
//    public ModelAndView listar(ModelMap model) {
//        model.addAttribute("usuarios", usuarioRepository.usuarios());
//        return new ModelAndView("/usuario/list");
//    }
//
//    @GetMapping("/form")
//    public String form(Usuario usuario, Model model){
//        model.addAttribute("usuario", new Usuario());
//        return "/usuario/form";
//    }
//
//    @PostMapping("/cadastrar")
//    public ModelAndView save(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult result) {
//        if(result.hasErrors()){
//            return new ModelAndView("/usuario/form");
//        }
//        usuario.setPassword(passwordEncoder.encode(usuario.getPassword())); // Criptografa a senha
//        usuarioRepository.save(usuario);
//        return new ModelAndView("redirect:/login?sucesso");
//    }
//
//    @GetMapping("/remove/{id}")
//    public ModelAndView remove(@PathVariable("id") Long id){
//        usuarioRepository.remove(id);
//        return new ModelAndView("redirect:/usuario/listar");
//    }
//
//    @GetMapping("/edit/{id}")
//    public ModelAndView edit(@PathVariable("id") Long id, ModelMap model) {
//        model.addAttribute("usuario", usuarioRepository.usuario(id));
//        return new ModelAndView("/usuario/form", model);
//    }
//
//    @PostMapping("/update")
//    public ModelAndView update(@Valid Usuario usuario, BindingResult result) {
//        if(result.hasErrors()){
//            return new ModelAndView("/usuario/form");
//        }
//        usuarioRepository.update(usuario);
//        return new ModelAndView("redirect:/usuario/listar");
//    }
//
//}
package br.edu.ifto.aula02.controller;

import br.edu.ifto.aula02.model.entity.Usuario;
import br.edu.ifto.aula02.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("usuario")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioController(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/listar")
    public ModelAndView listar(Model model) {
        model.addAttribute("usuarios", usuarioRepository.usuarios());
        return new ModelAndView("/usuario/list");
    }

    @GetMapping("/form")
    public String form(@ModelAttribute("usuario") Usuario usuario) {
        return "/usuario/form";
    }

    @PostMapping("/cadastrar")
    public ModelAndView save(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("/usuario/form");
        }
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword())); // Criptografa a senha
        usuarioRepository.save(usuario);
        return new ModelAndView("redirect:/login?sucesso");
    }

    @GetMapping("/remove/{id}")
    public ModelAndView remove(@PathVariable("id") Long id) {
        usuarioRepository.remove(id);
        return new ModelAndView("redirect:/usuario/listar");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id, Model model) {
        Usuario usuario = usuarioRepository.usuario(id);
        if (usuario.isEnabled()) {
            model.addAttribute("usuario", usuario);
            return new ModelAndView("/usuario/form");
        }
        return new ModelAndView("redirect:/usuario/listar");
    }

    @PostMapping("/update")
    public ModelAndView update(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("/usuario/form");
        }
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword())); // Garante que a senha continue criptografada
        usuarioRepository.save(usuario);
        return new ModelAndView("redirect:/usuario/listar");
    }
}
