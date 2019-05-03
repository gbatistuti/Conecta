package br.com.projeto.conecta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.projeto.conecta.service.DemandaService;

@Controller
public class HomeLiderController {

	@Autowired
	private DemandaService demandaService;
	
	@GetMapping("/homeLider")
	public String listar(ModelMap model) {
		model.addAttribute("demandas", demandaService.buscarTodos());
		return "homeLider";
	}
	
}
