package br.com.fabios.web.controllers;

import br.com.fabios.web.models.CalculoJurosCompostos;
import br.com.fabios.web.models.JurosCompostos;
import br.com.fabios.web.repository.CalculoJurosCompostosRepository;
import br.com.fabios.web.servico.CookieService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private CalculoJurosCompostosRepository calculoRepository;

    @GetMapping("/")
    public String index(Model model, HttpServletRequest request) throws UnsupportedEncodingException {
        List<CalculoJurosCompostos> jurosCompostosList = (List<CalculoJurosCompostos>)calculoRepository.findAll();
        model.addAttribute("calTotais", jurosCompostosList);
        model.addAttribute("nome", CookieService.getCookie(request, "nomeUsuario"));
        model.addAttribute("imgPerfil", CookieService.getCookie(request, "imgPerfil"));
        model.addAttribute("nivelAcesso", CookieService.getCookie(request, "nivelAcesso"));
        return "home/index";
    }
}
