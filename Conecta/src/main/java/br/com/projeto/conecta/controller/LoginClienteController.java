package br.com.projeto.conecta.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/loginCliente")
public class LoginClienteController {

	//@Autowired
	//private Cliente cliente;
	
	@PostMapping("logar")
	public String efetuarLogin(/*Cliente cliente*/) {
	return "";
	}
} 