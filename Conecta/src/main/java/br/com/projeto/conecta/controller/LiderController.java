package br.com.projeto.conecta.controller;

import java.time.LocalTime;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.projeto.conecta.domain.Agendamento;
import br.com.projeto.conecta.domain.Alocacoes;
import br.com.projeto.conecta.domain.Pedido;
import br.com.projeto.conecta.domain.Recusado;
import br.com.projeto.conecta.domain.Usuarios;
import br.com.projeto.conecta.security.ConectaUserDetailsService;
import br.com.projeto.conecta.service.AgendamentoService;
import br.com.projeto.conecta.service.AlocacaoService;
import br.com.projeto.conecta.service.DisponivelService;
import br.com.projeto.conecta.service.PedidoService;
import br.com.projeto.conecta.service.RecusadoService;

@Controller
@RequestMapping("/homeLider")
public class LiderController {

	@Autowired
	private AgendamentoService agendamentoService;
	@Autowired
	private AlocacaoService alocacaoService;
	@Autowired
	private ConectaUserDetailsService sessao;
	@Autowired
	private DisponivelService disponivelService;
	@Autowired
	private PedidoService pedidoService;
	@Autowired
	private RecusadoService recusadoService;

	@GetMapping
	public String listarAgendamentos(ModelMap model, HttpServletRequest request) {
		// Usuarios usuario = sessao.getCurrentUser();
		// request.setAttribute("nome", usuario.getNome());
		model.addAttribute("agendamento", agendamentoService.buscarPorStatus());
		model.addAttribute("disponiveis", disponivelService.buscarTodos());
		return "homeLider";
	}

	@PostMapping("/aprovar")
	@Transactional
	public String aprovarAgendamento(Alocacoes alocacao, Agendamento agendamento) {
		
		agendamento.getPedido().setStatus("aprovado");
		agendamento.getPedido().getProjeto().setQtdCreditos(alocacaoService.creditosParaDescontar(agendamento));

		LocalTime horaInicio = alocacaoService.buscaUltimaHora(agendamento);

		alocacao.setCriadoPor(sessao.getCurrentLider());
		alocacao.setHoraInicio(horaInicio);
		alocacao.setHoraFim(alocacaoService.definirHoraFim(horaInicio, alocacao));

		agendamentoService.salvarAgendamento(agendamento);
		alocacaoService.salvarAlocacao(alocacao);
		return "redirect:/homeLider";
	}

	@PostMapping("/reprovar")
	public String reprovarAgendamento(Recusado recusado, Agendamento agendamento) {
		recusado.setCriadoPor(sessao.getCurrentUser());

		Agendamento agendamentoAlterado = agendamentoService.getAgendamento(agendamento.getIdAgendamento());

		agendamentoAlterado.getPedido().setStatus("recusado");

		recusadoService.salvarRecusado(recusado);
		agendamentoService.salvarAgendamento(agendamentoAlterado);

		return "redirect:/homeLider";

	}

	@GetMapping("/alocacao")
	public String ListarDisponiveis(ModelMap model, HttpServletRequest request) {
		model.addAttribute("pedidos", pedidoService.buscarPorStatus());
		model.addAttribute("disponiveis", disponivelService.buscarTodos());
		Usuarios usuario = sessao.getCurrentUser();
		request.setAttribute("nome", usuario.getNome());
		return "alocacao";
	}

	@PostMapping("/alocacao/alocar")
	public String alocarDisponivelAoPedido(Agendamento agendamento, Pedido pedido, Alocacoes alocacoes) {
		Usuarios usuario = sessao.getCurrentUser();

		Pedido pedidoSelecionado = pedidoService.getPedido(pedido.getIdPedido());
		pedidoService.salvarPedido(pedidoSelecionado);

		agendamento = new Agendamento(agendamento.getDisponivel(), usuario, pedidoSelecionado);
		agendamentoService.salvarAgendamento(agendamento);

		return "redirect:/homeLider/alocacao";
	}

	@GetMapping("/acompanhamento")
	public String listarAgendamentosAprovadosEReprovados(ModelMap model) {
		model.addAttribute("aprovados", alocacaoService.buscarTodos());
		model.addAttribute("reprovados", recusadoService.buscarTodos());
		return "acompanhamentoLider";
	}

}
