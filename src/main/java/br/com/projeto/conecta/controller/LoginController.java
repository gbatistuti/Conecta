package br.com.projeto.conecta.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

	//@Autowired
	//private Perfil perfil;
	
	@PostMapping("/logar")
	public String efetuarLogin(/*Perfil perfil*/) {
		//verificação de perfis de acordo com os dados preenchidos
		return "";
	}
	
}