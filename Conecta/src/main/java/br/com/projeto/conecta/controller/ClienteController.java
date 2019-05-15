package br.com.projeto.conecta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.projeto.conecta.service.DisponivelService;

@Controller
@RequestMapping("/homeCliente")
public class ClienteController {

	@Autowired
	private DisponivelService disponivelService;
	
	@GetMapping
	public String listarDisponiveis(ModelMap model) {
		model.addAttribute("disponiveis",disponivelService.buscarTodos());
		return "homeCliente";
	}
	
}