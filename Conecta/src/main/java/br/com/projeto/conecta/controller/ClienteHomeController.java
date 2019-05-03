package br.com.projeto.conecta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.projeto.conecta.repository.ConsultorRepository;

@Controller
@RequestMapping("/clientehome")
public class ClienteHomeController {
	
	@Autowired
	private ConsultorRepository consultorRepository;
	
	@GetMapping("")
	public String listar(ModelMap model) {
		model.addAttribute("disponiveis", consultorRepository.findAll());
		return "/clientehome";
	}	
}