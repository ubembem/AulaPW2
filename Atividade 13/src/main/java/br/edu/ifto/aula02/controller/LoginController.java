package br.edu.ifto.aula02.controller;

import br.edu.ifto.aula02.model.entity.Usuario;
import br.edu.ifto.aula02.repository.RoleRepository;
import br.edu.ifto.aula02.repository.UsuarioRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Transactional
@Controller
@RequestMapping("login")
public class LoginController {
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping()
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout,
                        Model model) {
        if (error != null) {
            model.addAttribute("mensagemErro", "Usuário ou senha inválidos.");
        }
        if (logout != null) {
            model.addAttribute("mensagemSucesso", "Você saiu do sistema.");
        }
        return "login/login";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/login?logout"; // Redireciona para a página de login com uma mensagem de logout
    }

}