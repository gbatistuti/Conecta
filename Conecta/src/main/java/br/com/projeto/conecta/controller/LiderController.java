package br.com.projeto.conecta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.projeto.conecta.service.AgendamentoService;
import br.com.projeto.conecta.service.PedidoService;
import br.com.projeto.conecta.service.ProjetoService;

@Controller
@RequestMapping("/homeLider")
public class LiderController {

	@Autowired
	private AgendamentoService agendamentoService;
	@Autowired
	private ProjetoService projetoService;
	@Autowired
	private PedidoService pedidoService;

	@GetMapping
	public String ListarAgendamentos(ModelMap model) {
		model.addAttribute("agendamento", agendamentoService.BuscarTodos());
		return "homeLider";
	}
	
	@GetMapping("/gerenciaProjetos")
	public String listar(ModelMap model) {
		model.addAttribute("projetos", projetoService.buscarTodos());
		return "gerenciaProjetos";
	}
	
	@GetMapping("/totinhas")
	public String ListarDisponiveis(ModelMap model) {
		model.addAttribute("pedidos", pedidoService.buscarTodos());
		return "alocacao";
	}
	
	
}