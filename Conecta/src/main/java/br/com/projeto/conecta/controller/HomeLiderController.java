package br.com.projeto.conecta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.projeto.conecta.security.ConectaUserDetailsService;
import br.com.projeto.conecta.service.AgendamentoService;

@Controller
public class HomeLiderController {

	@Autowired
	private AgendamentoService agendamentoService;
	@Autowired
	private ConectaUserDetailsService conecta;

	@GetMapping("/homeLider")
	public String ListarAgendamentos(ModelMap model) {
		model.addAttribute("agendamento", agendamentoService.BuscarTodos());
		
		Integer userId = conecta.getCurrentUserId();
		System.out.println(userId);
		
		
		return "homeLider";
	}
}