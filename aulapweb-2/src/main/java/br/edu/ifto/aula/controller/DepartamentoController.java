package br.edu.ifto.aula.controller;

import br.edu.ifto.aula.model.entity.Departamento;
import br.edu.ifto.aula.model.entity.Funcionario;
import br.edu.ifto.aula.model.repository.DepartamentoRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Transactional
@Scope("request")
@Controller
@RequestMapping("/departamento")
public class DepartamentoController {

    @Autowired
    DepartamentoRepository repository;

    @Autowired
    Departamento departamento; //O spring vai criar o objeto na session

    @GetMapping("/form")
    public String form(Departamento departamento,
                       Funcionario funcionario){
        return "/departamento/form";
    }

    @PostMapping("/funcionario/add")
    public ModelAndView funcionarioAdd(Funcionario funcionario){
        //add o funcionário enviando por parâmetro na lista do departamento da sessão
        departamento.getFuncionarios().add(funcionario);
        //associa o departamento ao funcionário
        funcionario.setDepartamento(departamento);
        return new ModelAndView("redirect:/departamento/form");
    }

    @PostMapping("/save")
    public ModelAndView save(Departamento departamento, HttpSession session){
        //atribui o nome informado para o departamento através do form para o objeto da sessão
        this.departamento.setNome(departamento.getNome());
        //salva o departamento no banco
        repository.save(this.departamento);
        session.invalidate();
        return new ModelAndView("redirect:/departamento/list");
    }

    @GetMapping("/list")
    public ModelAndView listar(ModelMap model) {
        model.addAttribute("departamentos", repository.departamentos());
        return new ModelAndView("/departamento/list"); //caminho para a view
    }

}

