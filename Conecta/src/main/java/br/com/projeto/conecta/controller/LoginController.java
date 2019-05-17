package br.com.projeto.conecta.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

	@GetMapping("/")
	public String index(HttpServletRequest request) {

		if (request.isUserInRole("ROLE_CLIENTE")) {
			String id = request.getRequestedSessionId();
			System.out.println(id);
			return "redirect:/homeCliente";
		} else if (request.isUserInRole("ROLE_CONSULTOR")) {
			return "redirect:/homeConsultor";
		} else if (request.isUserInRole("ROLE_LIDER")) {
			return "redirect:/homeLider";
		}
		
		return "paginaDeErro";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@PostMapping("/logout")
	public String logout() {
		return "login";
	}

}
