package br.com.fabios.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdministradoresController {
    @GetMapping("/administradores")
    public String index(Model model){
        return "administradores/index";
    }
}
