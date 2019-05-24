package br.com.projeto.conecta.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.projeto.conecta.domain.Agendamento;
import br.com.projeto.conecta.domain.Consultor;
import br.com.projeto.conecta.domain.Disponiveis;
import br.com.projeto.conecta.domain.Pedido;
import br.com.projeto.conecta.domain.Usuarios;
import br.com.projeto.conecta.security.ConectaUserDetailsService;
import br.com.projeto.conecta.service.AgendamentoService;
import br.com.projeto.conecta.service.DisponivelService;
import br.com.projeto.conecta.service.PedidoService;

@Controller
@RequestMapping("/homeConsultor")
public class ConsultorController {

	@Autowired
	private PedidoService pedidoService;
	@Autowired
	private ConectaUserDetailsService sessao;
	@Autowired
	private DisponivelService disponivelService;
	@Autowired
	private AgendamentoService agendamentoService;

	@GetMapping
	public String listarPedidos(ModelMap model, HttpServletRequest request) {

		Usuarios usuario = sessao.getCurrentUser();
		model.addAttribute("pedido", pedidoService.buscarTodos());
		model.addAttribute("pedidoCandidatado", agendamentoService.buscarCandidaturasByUsuario());
		request.setAttribute("nome", usuario.getNome());
		return "homeConsultor";
	}

	@PostMapping("/apontar")
	public String salvarApontamento(Disponiveis disponiveis) {
		
		if (disponivelService.validaApontamento() == null) {
			disponiveis.setConsultor(sessao.getCurrentConsultor());
			disponivelService.salvarApontamento(disponiveis);
			return "redirect:/homeConsultor?sucesso";
		}
		return "redirect:/homeConsultor?falha";

	}

	@PostMapping("/candidatar")
	public String candidatarAoPedido(Agendamento agendamento, Pedido pedido) {
		Consultor consultor = sessao.getCurrentConsultor();
		agendamento.setConsultor(consultor);
		agendamento.setPedido(pedido);
		agendamento.setCriadoPor(consultor);
		agendamentoService.salvarAgendamento(agendamento);
		return "redirect:/homeConsultor?candidatado";
	}

}