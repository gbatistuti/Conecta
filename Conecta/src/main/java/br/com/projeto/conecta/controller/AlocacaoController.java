package br.com.projeto.conecta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.projeto.conecta.service.PedidoService;

@Controller
public class AlocacaoController {

	@Autowired
	private PedidoService pedidoService;
	
	@GetMapping("/totinhas")
	public String ListarDisponiveis(ModelMap model) {
		model.addAttribute("pedidos", pedidoService.buscarTodos());
		return "alocacao";
	}
}
