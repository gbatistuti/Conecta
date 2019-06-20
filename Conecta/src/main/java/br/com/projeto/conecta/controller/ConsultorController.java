package br.com.projeto.conecta.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.projeto.conecta.domain.Agendamento;
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
	private AgendamentoService agendamentoService;
	@Autowired
	private ConectaUserDetailsService sessao;
	@Autowired
	private DisponivelService disponivelService;
	@Autowired
	private PedidoService pedidoService;

	@GetMapping
	public String listarPedidos(ModelMap model, HttpServletRequest request) {
		Usuarios usuario = sessao.getCurrentUser();
		request.setAttribute("nome", usuario.getNome());
		model.addAttribute("pedido", pedidoService.filtrarPorOrigemECandidatura());
		model.addAttribute("pedidoCandidatado", agendamentoService.buscarCandidaturasByUsuario(sessao.getCurrentUserEmail()));
		return "homeConsultor";
	}

	@PostMapping("/apontar")
	public String salvarApontamento(Disponiveis disponiveis) {
		if (disponivelService.validaApontamento(sessao.getCurrentUserEmail()) == null) {
			disponiveis.setConsultor(sessao.getCurrentConsultor());
			disponivelService.salvarApontamento(disponiveis);
			return "redirect:/homeConsultor?sucesso";
		}
		return "redirect:/homeConsultor?falha";
	}

	@PostMapping("/candidatar")
	public String candidatarAoPedido(Agendamento agendamento, Pedido pedido) {
		Disponiveis disponivel = disponivelService.validaApontamento(sessao.getCurrentUserEmail());
		
		if (disponivel == null) {
			return "redirect:/homeConsultor?falha2";
		}

		Usuarios usuario = disponivel.getConsultor();
		
		pedidoService.atualizarCandidaturaPedido(pedido.getIdPedido());
		
		agendamento = new Agendamento(disponivel, usuario, pedido);
		agendamentoService.salvarAgendamento(agendamento);
		return "redirect:/homeConsultor?candidatado";
	}

}