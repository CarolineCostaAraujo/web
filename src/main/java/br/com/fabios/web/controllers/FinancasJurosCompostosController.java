package br.com.fabios.web.controllers;

import br.com.fabios.web.models.Administrador;
import br.com.fabios.web.models.JurosCompostos;
import br.com.fabios.web.repository.AdministradoresRepository;
import br.com.fabios.web.repository.JurosCompostosRepository;
import br.com.fabios.web.servico.CookieService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class FinancasJurosCompostosController {

    @Autowired
    private JurosCompostosRepository repository;

    @Autowired
    private AdministradoresRepository usuarioRepository;
    @GetMapping("/calculadoraJurosCompostos")
    public String index(Model model, HttpServletRequest request) throws UnsupportedEncodingException {
        model.addAttribute("nome", CookieService.getCookie(request, "nomeUsuario"));
        model.addAttribute("imgPerfil", CookieService.getCookie(request, "imgPerfil"));
//        List<Administrador> administradores = (List<Administrador>)repository.findAll();
//        model.addAttribute("administradores", administradores);
        return "financasJurosCompostos/index";
    }

    @GetMapping("/calculadoraJurosCompostos/novo")
    public String novo(Model model, HttpServletRequest request) throws UnsupportedEncodingException {
        List<JurosCompostos> jurosCompostosList = (List<JurosCompostos>)repository.findAll();
        model.addAttribute("jurosCompostosList", jurosCompostosList);
        model.addAttribute("nome", CookieService.getCookie(request, "nomeUsuario"));
        model.addAttribute("imgPerfil", CookieService.getCookie(request, "imgPerfil"));
        return "financasJurosCompostos/novo";
    }

    @GetMapping("/calculadoraJurosCompostos/configuracaoJuros")
    public String configuracaoJuros(Model model, HttpServletRequest request) throws UnsupportedEncodingException {
        List<JurosCompostos> jurosCompostosList = (List<JurosCompostos>)repository.findAll();
        model.addAttribute("jurosCompostosList", jurosCompostosList);
        model.addAttribute("nome", CookieService.getCookie(request, "nomeUsuario"));
        model.addAttribute("imgPerfil", CookieService.getCookie(request, "imgPerfil"));
        return "financasJurosCompostos/configuracaoJuros";
    }

    @GetMapping("/calculadoraJurosCompostos/novoPlanoDeJuros")
    public String novoconfiguracaoJuros(Model model, HttpServletRequest request) throws UnsupportedEncodingException {
        model.addAttribute("nome", CookieService.getCookie(request, "nomeUsuario"));
        model.addAttribute("imgPerfil", CookieService.getCookie(request, "imgPerfil"));
        return "financasJurosCompostos/novoPlanoDeJuros";
    }

    @PostMapping("/calculadoraJurosCompostos/novoPlanoDeJuros/criar")
    public String criar(JurosCompostos jcTaxas, Model model,HttpServletRequest request) throws UnsupportedEncodingException {
        model.addAttribute("nome", CookieService.getCookie(request, "nomeUsuario"));
        String nomeUsuario = (String) model.getAttribute("nome");
        JurosCompostos jc = new JurosCompostos();
        jc.setFee(jcTaxas.getFee()/100);
        jc.setIofConfig(jcTaxas.getIofConfig()/100);
        jc.setTxAdmConfig(jcTaxas.getTxAdmConfig()/100);
        jc.setName(jcTaxas.getName());
        jc.setDate(new Date());
        jc.setUsuario(nomeUsuario);
        repository.save(jc);
        return "redirect:/calculadoraJurosCompostos/configuracaoJuros";
    }
//
//    @GetMapping("/administradores/{id}")
//    public String busca(@PathVariable int id, Model model){
//        Optional<Administrador> admin = repository.findById(id);
//        try {
//            model.addAttribute("administrador", admin.get());
//        }
//        catch (Exception error){
//            return "redirect:/administradores";
//        }
//        return "/administradores/editar";
//    }
//
//    @PostMapping("/administradores/{id}/atualizar")
//    public String atualizar(@PathVariable int id, Administrador administrador){
//        if(!repository.exist(id)){
//            return "redirect:/administradores";
//        }
//
//        repository.save(administrador);
//        return "redirect:/administradores";
//    }
//
//    @GetMapping("/administradores/{id}/excluir")
//    public String excluir(@PathVariable int id){
//        repository.deleteById(id);
//        return "redirect:/administradores";
//    }
}
