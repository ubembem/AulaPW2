package br.edu.ifto.aula02.controller;

import br.edu.ifto.aula02.model.dao.VeiculoDAO;
import br.edu.ifto.aula02.model.entity.Veiculo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("veiculo")
public class VeiculoController {
    VeiculoDAO veiculoDAO;

    public VeiculoController() {
        this.veiculoDAO = new VeiculoDAO();
    }

    @GetMapping("/listar")
    public ModelAndView listar(ModelMap model) {
        model.addAttribute("veiculos", veiculoDAO.buscarVeiculos());
        return new ModelAndView("/veiculo/list");
    }
    /**
     * @param veiculo necessário devido utilizar no form.html o th:object que faz referência ao objeto esperado no controller.
     * @return
     */
    @GetMapping("/form")
    public String form(Veiculo veiculo){
        return "/veiculo/form";
    }

    @PostMapping("/save")
    public ModelAndView save(Veiculo veiculo){
        veiculoDAO.save(veiculo);
        return new ModelAndView("redirect:/veiculo/listar");
    }

    /**
     * @param id
     * @return
     * @PathVariable é utilizado quando o valor da variável é passada diretamente na URL
     */
    @GetMapping("/remove/{id}")
    public ModelAndView remove(@PathVariable("id") Long id){
        veiculoDAO.remove(id);
        return new ModelAndView("redirect:/veiculo/listar");
    }

    /**
     * @param id
     * @return
     * @PathVariable é utilizado quando o valor da variável é passada diretamente na URL
     */
    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("veiculo", veiculoDAO.buscarVeiculo(id));
        return new ModelAndView("/veiculo/form", model);
    }

    @PostMapping("/update")
    public ModelAndView update(Veiculo veiculo) {
        veiculoDAO.update(veiculo);
        return new ModelAndView("redirect:/veiculo/listar");
    }

}
