package br.com.fabios.web.controllers;

import br.com.fabios.web.models.Administrador;
import br.com.fabios.web.repository.AdministradoresRepository;
import br.com.fabios.web.servico.CookieService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

@Controller
public class AdministradoresController {

    public void cookies (Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        model.addAttribute("nivelAcesso", CookieService.getCookie(request, "nivelAcesso"));
        model.addAttribute("nome", CookieService.getCookie(request, "nomeUsuario"));
        model.addAttribute("imgPerfil", CookieService.getCookie(request, "imgPerfil"));
        if(CookieService.getCookie(request, "nivelAcesso").equals("funcionario")){
            response.sendRedirect("/login");
        }
    }

    @Autowired
    private AdministradoresRepository repository;
    @GetMapping("/administradores")
    public String index(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        cookies(model,request,response);
            List<Administrador> administradores = (List<Administrador>) repository.findAll();
            model.addAttribute("administradores", administradores);
            return "administradores/index";
    }

    @GetMapping("/administradores/novo")
    public String novo(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        cookies(model,request,response);
        return "administradores/novo";
    }

    @PostMapping("/administradores/criar")
    public String criar(Administrador administrador, Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        cookies(model,request,response);
        repository.save(administrador);
        return "redirect:/administradores";
    }

    @GetMapping("/administradores/{id}")
    public String busca(@PathVariable int id, Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        cookies(model,request,response);
        Optional<Administrador> admin = repository.findById(id);
        try {
            model.addAttribute("administrador", admin.get());
        }
        catch (Exception error){
            return "redirect:/administradores";
        }
        return "administradores/editar";
    }

    @PostMapping("/administradores/{id}/atualizar")
    public String atualizar(@PathVariable int id, Administrador administrador,Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        cookies(model,request,response);
        if(!repository.exist(id)){
            return "redirect:/administradores";
        }

        repository.save(administrador);
        return "redirect:/administradores";
    }

    @GetMapping("/administradores/{id}/excluir")
    public String excluir(@PathVariable int id){
        repository.deleteById(id);
        return "redirect:/administradores";
    }
}
