package br.com.projeto.conecta.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.projeto.conecta.domain.Cliente;
import br.com.projeto.conecta.domain.Consultor;
import br.com.projeto.conecta.domain.Lider;
import br.com.projeto.conecta.domain.Usuarios;
import br.com.projeto.conecta.infra.SegurancaLogin;

@Controller
public class LoginController {
	
	private final SegurancaLogin segurancaLogin;
	
	public LoginController(SegurancaLogin segurancaLogin) {
		this.segurancaLogin = segurancaLogin;
	}

	@PostMapping("/login")
	public String efetuarLogin(@ModelAttribute(name="usuarios") Usuarios usuarios, Model model) {
		
		Integer idUsuarioLogado = segurancaLogin.permitirAcesso(usuarios.getEmail(), usuarios.getSenha());
		 if(idUsuarioLogado != -1) {
			 String grupoUsuarioLogado = segurancaLogin.validarGrupo(idUsuarioLogado);
			 if(grupoUsuarioLogado.equals("lider"))return "homeLider";
			 else if (grupoUsuarioLogado.equals("consultor"))return "homeConsultor";
			 else if(grupoUsuarioLogado.equals("cliente"))return "homeCliente";
		 }
		 return "login";

	}
	
	@GetMapping("/login")
	public String exibirPaginaDeLogin(Model model) {
		model.addAttribute(new Usuarios(null, null, null, null, null, null));
		return "login";
	}
}