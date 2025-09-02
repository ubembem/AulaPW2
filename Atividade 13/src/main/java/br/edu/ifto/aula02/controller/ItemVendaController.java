package br.edu.ifto.aula02.controller;


import br.edu.ifto.aula02.model.entity.ItemVenda;
import br.edu.ifto.aula02.repository.ItemVendaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Transactional
@Controller
@RequestMapping("itemVenda")
public class ItemVendaController {
    @Autowired
    ItemVendaRepository itemVendaRepository;

    @GetMapping("/listar")
    public ModelAndView listar(ModelMap model) {
        model.addAttribute("itensVenda", itemVendaRepository.itensVenda());
        return new ModelAndView("/itemVenda/list");
    }
    /**
     * @param itemVenda necessário devido utilizar no form o th:object que faz referência ao objeto esperado no controller.
     * @return
     */
    @GetMapping("/form")
    public String form(ItemVenda itemVenda){
        return "/itemVenda/form";
    }

    @PostMapping("/save")
    public ModelAndView save(ItemVenda itemVenda){
        itemVendaRepository.save(itemVenda);
        return new ModelAndView("redirect:/itemVenda/listar");
    }

    /**
     * @param id
     * @return
     * @PathVariable é utilizado quando o valor da variável é passada diretamente na URL
     */
    @GetMapping("/remove/{id}")
    public ModelAndView remove(@PathVariable("id") Long id){
        itemVendaRepository.remove(id);
        return new ModelAndView("redirect:/itemVenda/listar");
    }

    /**
     * @param id
     * @return
     * @PathVariable é utilizado quando o valor da variável é passada diretamente na URL
     */
    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("itemVenda", itemVendaRepository.itemVenda(id));
        return new ModelAndView("/itemVenda/form", model);
    }

    @PostMapping("/update")
    public ModelAndView update(ItemVenda itemVenda) {
        itemVendaRepository.update(itemVenda);
        return new ModelAndView("redirect:/itemVenda/listar");
    }

}
