package br.edu.ifto.aula02.controller;

import br.edu.ifto.aula02.model.entity.Role;
import br.edu.ifto.aula02.repository.RoleRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("role")
public class RoleController {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleController(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @GetMapping("/listar")
    public ModelAndView listar(Model model) {
        model.addAttribute("roles", roleRepository.findAll());
        return new ModelAndView("/role/list");
    }

    @GetMapping("/form")
    public String form(@ModelAttribute("role") Role role) {
        return "/role/form";
    }

    @PostMapping("/cadastrar")
    public ModelAndView save(@Valid @ModelAttribute("role") Role role, BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("/role/form");
        }
        roleRepository.save(role);
        return new ModelAndView("redirect:/role/listar");
    }

    @GetMapping("/remove/{id}")
    public ModelAndView remove(@PathVariable("id") Long id) {
        roleRepository.deleteById(id);
        return new ModelAndView("redirect:/role/listar");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id, Model model) {
        Optional<Role> role = roleRepository.findById(id);
        if (role.isPresent()) {
            model.addAttribute("role", role.get());
            return new ModelAndView("/role/form");
        }
        return new ModelAndView("redirect:/role/listar");
    }

    @PostMapping("/update")
    public ModelAndView update(@Valid @ModelAttribute("role") Role role, BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("/role/form");
        }
        roleRepository.save(role);
        return new ModelAndView("redirect:/role/listar");
    }
}
