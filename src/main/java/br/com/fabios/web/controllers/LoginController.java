package br.com.fabios.web.controllers;

import br.com.fabios.web.models.Administrador;
import br.com.fabios.web.repository.AdministradoresRepository;
import br.com.fabios.web.servico.CookieService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.UnsupportedEncodingException;

@Controller
public class LoginController {

    @Autowired
    private AdministradoresRepository repository;
    @GetMapping("/login")
    public String index(){
        return "login/index";
    }

    @PostMapping("/logar")
    public String logar(Model model, Administrador admParam, String lembrar, HttpServletResponse response) throws UnsupportedEncodingException {
        Administrador adm = this.repository.login(admParam.getEmail(),admParam.getSenha());
        if (adm.getNivelAcesso().equals("expirado")){
            model.addAttribute("expirado", "Usuário expirado, por gentileza entrar em contato com o Administrador");
            return "login/index";
        }

        if (adm != null){
            int tempoLogado = (60*60); //1 hora de cookie
            if (lembrar != null){
                tempoLogado = (60*60*24*365); // 1 ano de cookie
            }
            CookieService.setCookie(response,"usuarioId",String.valueOf(adm.getId()), tempoLogado);
            CookieService.setCookie(response,"nomeUsuario",String.valueOf(adm.getNome()), tempoLogado);
            CookieService.setCookie(response,"imgPerfil",String.valueOf(adm.getImgPerfil()), tempoLogado);
            return "redirect:/";
        }
        model.addAttribute("erro", "Usuário ou senha inválidos");
        return "login/index";
    }

    @GetMapping("/sair")
    public String sair(HttpServletResponse response) throws UnsupportedEncodingException {
            CookieService.setCookie(response,"usuarioId","", 0);
            return "redirect:/login";
    }
}
