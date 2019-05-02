package br.com.projeto.conecta.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.projeto.conecta.domain.Cliente;
import br.com.projeto.conecta.domain.Consultor;
import br.com.projeto.conecta.domain.Lider;
import br.com.projeto.conecta.infra.SegurancaLogin;

@Controller
public class LoginController {
	
	private final SegurancaLogin segurancaLogin;
	
	public LoginController(SegurancaLogin segurancaLogin) {
		this.segurancaLogin = segurancaLogin;
	}

	@PostMapping("/login")
	public String efetuarLogin(@ModelAttribute(name="lider") Lider lider, Model model) {
		
		
		if(segurancaLogin.permitirAcesso(lider.getEmail(), lider.getSenha()))return "homeCliente";
		else return "login";

	}
	
	@GetMapping("/login")
	public String exibirPaginaDeLogin(Model model) {
		model.addAttribute(new Lider(null, null, null, null, null, null));
		model.addAttribute(new Cliente(null, null, null, null, null));
		model.addAttribute(new Consultor(null, null, null, null, null, null, null));
		return "login";
	}
	
}