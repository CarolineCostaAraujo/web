package br.com.fabios.web.controllers;

import br.com.fabios.web.servico.CookieService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.UnsupportedEncodingException;

@Controller
public class HomeController {
    @GetMapping("/")
    public String index(Model model, HttpServletRequest request) throws UnsupportedEncodingException {
        model.addAttribute("nome", CookieService.getCookie(request, "nomeUsuario"));
        model.addAttribute("imgPerfil", CookieService.getCookie(request, "imgPerfil"));
        model.addAttribute("nivelAcesso", CookieService.getCookie(request, "nivelAcesso"));
        return "home/index";
    }
}
