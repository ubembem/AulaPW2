package aulasPW2.Aula1.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/pessoa")
public class pessoaController {
    @ResponseBody
    @GetMapping("hello")
    public String hello(@RequestParam(value="name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }
}
