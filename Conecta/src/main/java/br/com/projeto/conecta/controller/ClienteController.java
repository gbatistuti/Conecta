package br.com.projeto.conecta.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.projeto.conecta.domain.Agendamento;
import br.com.projeto.conecta.domain.Pedido;
import br.com.projeto.conecta.security.ConectaUserDetailsService;
import br.com.projeto.conecta.service.AgendamentoService;
import br.com.projeto.conecta.service.DisponivelService;
import br.com.projeto.conecta.service.PedidoService;
import br.com.projeto.conecta.service.ProjetoService;

@Controller
@RequestMapping("/homeCliente")
public class ClienteController {

	@Autowired
	private DisponivelService disponivelService;
	@Autowired
	private AgendamentoService agendamentoService;
	@Autowired
	private PedidoService pedidoService;
	@Autowired
	private ProjetoService projetoService;
	@Autowired
	private ConectaUserDetailsService sessao;
	@GetMapping
	public String listarDisponiveis(ModelMap model) {
		model.addAttribute("disponiveis",disponivelService.buscarTodos());
		model.addAttribute("projeto",projetoService.buscarPor(sessao.getCurrentUserId()));
		return "homeCliente";
	}
	
//	@GetMapping
//	public void listarProjetos(ModelMap model) {
//		model.addAttribute("projeto",projetoService.buscarPor(sessao.getCurrentUserId()));
//	}
	
	@PostMapping("/criarpedido")
	public String criarPedido(Pedido pedido) {
		pedidoService.salvarPedido(pedido);
		return "redirect:/homeCliente";
	}
	
	@PostMapping(value="/homeCliente/criaragendamento")
	public String criarAgendamento(Agendamento agendamento) {
		
		return "homeCliente";
	}
}