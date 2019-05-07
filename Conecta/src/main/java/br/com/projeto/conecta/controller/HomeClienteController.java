package br.com.projeto.conecta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.projeto.conecta.service.DisponivelService;
import br.com.projeto.conecta.service.HabilidadesEspecificasService;

@Controller
public class HomeClienteController {

	@Autowired
	//private ConsultorService consultorService;
	private DisponivelService disponivelService;
	private HabilidadesEspecificasService habilidadesEspecificasService;
	
	@GetMapping("/homeCliente")
	public String listar(ModelMap model) {
		//model.addAttribute("disponiveis",disponivelService.buscarTodos());
		//model.addAttribute("habilidadesEspecificas", habilidadesEspecificasService.buscarComId(id);
		model.addAttribute("habilidadesEspecificas", habilidadesEspecificasService.buscarTodos());
		return "homeCliente";
	}
	

}