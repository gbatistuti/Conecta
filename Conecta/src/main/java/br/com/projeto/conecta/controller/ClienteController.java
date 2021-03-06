package br.com.projeto.conecta.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.projeto.conecta.domain.Agendamento;
import br.com.projeto.conecta.domain.Pedido;
import br.com.projeto.conecta.domain.Usuario;
import br.com.projeto.conecta.security.ConectaUserDetailsService;
import br.com.projeto.conecta.service.AgendamentoService;
import br.com.projeto.conecta.service.DisponivelService;
import br.com.projeto.conecta.service.MensagemService;
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
	@Autowired
	private MensagemService mensagemService;
	
	@GetMapping
	public String listarDisponiveis(ModelMap model, HttpServletRequest request) {
		Usuario usuario = sessao.getCurrentUser();
		request.setAttribute("nome", usuario.getNome());
		
		model.addAttribute("projeto",projetoService.buscarPor(sessao.getCurrentUserEmail()));
		model.addAttribute("disponiveis",disponivelService.buscarTodos());
		model.addAttribute("pedido", new Pedido());
		model.addAttribute("agendamento", new Agendamento());
		return "homeCliente";
	}
	
	@PostMapping("/criarpedido")
	public String criarPedido(Pedido pedido, Model model) {
		Usuario usuario = sessao.getCurrentUser();
		
		pedido.setCriadoPor(usuario);
		pedido.setOrigem("Pedido");
		pedidoService.salvarPedido(pedido);
		
		mensagemService.emailCriacaoPedido(pedido, usuario);
		
		return "redirect:/homeCliente?pedidoCriado";
	}
	
	@PostMapping("/criaragendamento")
	public String criarAgendamento(Pedido pedido, Agendamento agendamento, Model model) {
		Usuario usuario = sessao.getCurrentUser();
		
		pedido.setCriadoPor(usuario);
		pedido.setOrigem("Agendamento");
		
		agendamento.setPedido(pedido);
		agendamento.setCriadoPor(usuario);
		
		if (agendamentoService.validaHoras(agendamento) == false) {
			return "redirect:/homeCliente?erroAoCriarAgendamentoPorhoras";
		} else if (agendamentoService.validaCreditos(agendamento) == false) {
			return "redirect:/homeCliente?erroAoCriarAgendamentoPorCreditos";
		}
		pedidoService.salvarPedido(pedido);
		agendamentoService.salvarAgendamento(agendamento);
		
		mensagemService.emailCriacaoAgendamento(usuario, agendamento);
		
		return "redirect:/homeCliente?agendamentoCriado";
	}
	
	@GetMapping("/acompanhamentoCliente")
	public String listarPedidosEAgendamentos(Pedido pedido, Agendamento agendamento, Model model, HttpServletRequest request) {
		Usuario usuario = sessao.getCurrentUser();
		request.setAttribute("nome", usuario.getNome());
		
		model.addAttribute("pedidosRealizados", pedidoService.buscarPedidosPorUsuario(usuario));
		model.addAttribute("agendamentosCriados", agendamentoService.buscarAgendamentosPorUsuario(usuario));
		return "AcompanhamentoCliente";
	}
}
