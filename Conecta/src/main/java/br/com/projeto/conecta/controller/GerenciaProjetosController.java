package br.com.projeto.conecta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.projeto.conecta.service.ProjetoService;

@Controller
public class GerenciaProjetosController {

	@Autowired
	private ProjetoService projetoService;
	
	
	@GetMapping("/gerenciaProjetos")
	public String listar(ModelMap model) {
		model.addAttribute("projetos", projetoService.buscarTodos());
		return "gerenciaProjetos";
	}
	
}
