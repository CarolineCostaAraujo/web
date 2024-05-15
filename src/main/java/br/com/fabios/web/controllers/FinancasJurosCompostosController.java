package br.com.fabios.web.controllers;

import br.com.fabios.web.models.Administrador;
import br.com.fabios.web.models.CalculoJurosCompostos;
import br.com.fabios.web.models.JurosCompostos;
import br.com.fabios.web.repository.AdministradoresRepository;
import br.com.fabios.web.repository.CalculoJurosCompostosRepository;
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
    private CalculoJurosCompostosRepository calculoRepository;

    @Autowired
    private AdministradoresRepository usuarioRepository;
    @GetMapping("/calculadoraJurosCompostos")
    public String index(Model model, HttpServletRequest request) throws UnsupportedEncodingException {
        request.setAttribute("role", "GESTOR");
        model.addAttribute("nome", CookieService.getCookie(request, "nomeUsuario"));
        model.addAttribute("imgPerfil", CookieService.getCookie(request, "imgPerfil"));
        List<CalculoJurosCompostos> calTotais = (List<CalculoJurosCompostos>)calculoRepository.findAll();
        model.addAttribute("calTotais", calTotais);
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
    public String criarPlanoDeJuros(JurosCompostos jcTaxas, Model model,HttpServletRequest request) throws UnsupportedEncodingException {
        model.addAttribute("nome", CookieService.getCookie(request, "nomeUsuario"));
        model.addAttribute("imgPerfil", CookieService.getCookie(request, "imgPerfil"));
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

    @PostMapping("/calculadoraJurosCompostos/criar")
    public String criarCalculo(JurosCompostos jurosCompostos, Model model,HttpServletRequest request, CalculoJurosCompostos calculoJurosCompostos) throws UnsupportedEncodingException {
        model.addAttribute("nome", CookieService.getCookie(request, "nomeUsuario"));
        model.addAttribute("imgPerfil", CookieService.getCookie(request, "imgPerfil"));
        String nomeUsuario = (String) model.getAttribute("nome");
        Administrador usuario = usuarioRepository.findbyName(nomeUsuario);
        calculoJurosCompostos.setCliente(usuario);
        calculoJurosCompostos.setDate(new Date());
        calculoJurosCompostos.setJurosCompostos(jurosCompostos);
        calculoRepository.save(calculoJurosCompostos);
        return "redirect:/calculadoraJurosCompostos";
    }

    @GetMapping("/calculadoraJurosCompostos/calculoMensal/{id}")
    public String buscaMensal(@PathVariable int id, Model model, HttpServletRequest request) throws UnsupportedEncodingException {
        model.addAttribute("nome", CookieService.getCookie(request, "nomeUsuario"));
        model.addAttribute("imgPerfil", CookieService.getCookie(request, "imgPerfil"));
        Optional<CalculoJurosCompostos> calculoJurosCompostos = calculoRepository.findById(id);
        try {
            model.addAttribute("calculoJurosCompostos", calculoJurosCompostos.get());
        }
        catch (Exception error){
            return "redirect:/calculadoraJurosCompostos";
        }
        return "/financasJurosCompostos/calculoMensal";
    }

    @GetMapping("/calculadoraJurosCompostos/calculoAnual/{id}")
    public String buscaAnual(@PathVariable int id, Model model, HttpServletRequest request) throws UnsupportedEncodingException {
        model.addAttribute("nome", CookieService.getCookie(request, "nomeUsuario"));
        model.addAttribute("imgPerfil", CookieService.getCookie(request, "imgPerfil"));
        Optional<CalculoJurosCompostos> calculoJurosCompostos = calculoRepository.findById(id);
        try {
            model.addAttribute("calculoJurosCompostos", calculoJurosCompostos.get());
        }
        catch (Exception error){
            return "redirect:/calculadoraJurosCompostos";
        }
        return "/financasJurosCompostos/calculoAnual";
    }
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
