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

		alocacao.setCriadoPor(sessao.getCurrentLider());
		LocalTime horaInicio = alocacaoService.buscaUltimaHora(agendamento);

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
	public String alocarDisponivelAoPedido(Agendamento agendamento, Pedido pedido, Alocacoes alocacao) {
		Usuarios usuario = sessao.getCurrentUser();

		Pedido pedidoCandidatado = pedidoService.getPedido(pedido.getIdPedido());
		pedidoCandidatado.setStatus("aprovado");
		
		agendamento = new Agendamento(agendamento.getDisponivel(), usuario, pedidoCandidatado);
		agendamentoService.salvarAgendamento(agendamento);
		
		float creditosPorHora = agendamento.getDisponivel().getConsultor().getCreditosPorHora();
		System.out.println("------------------------------------creditos por hora: "+creditosPorHora);
		int horasConstratadas = pedidoCandidatado.getSugestaoDeHoras();
		System.out.println("------------------------------------horas contratadas: "+horasConstratadas);
		float creditosDoProjeto = agendamento.getPedido().getProjeto().getQtdCreditos();
		System.out.println("------------------------------------creditos DO projeto: "+creditosDoProjeto);
		float creditosParaDescontar = (creditosPorHora * horasConstratadas);
		System.out.println("------------------------------------creditos para descontar: "+creditosParaDescontar);
		

		agendamento.getPedido().getProjeto().setQtdCreditos(creditosDoProjeto - creditosParaDescontar);
		
		//agendamento.getPedido().getProjeto().setQtdCreditos(alocacaoService.creditosParaDescontar(agendamento));
		LocalTime horaInicio = alocacaoService.buscaUltimaHora(agendamento);
		alocacao.setCriadoPor(sessao.getCurrentLider());
		alocacao.setHoraInicio(horaInicio);
		System.out.println("------------------------------------- hora inicio"+alocacao.getHoraInicio());
		alocacao.setAgendamento(agendamento);		
		alocacao.setHoraFim(alocacaoService.definirHoraFim(horaInicio, alocacao));
		alocacao.setMotivo("Alocação criada a partir da lista de pedidos.");

		alocacaoService.salvarAlocacao(alocacao);
		return "redirect:/homeLider/alocacao";
	}

	@GetMapping("/acompanhamento")
	public String listarAgendamentosAprovadosEReprovados(ModelMap model) {
		model.addAttribute("aprovados", alocacaoService.buscarTodos());
		model.addAttribute("reprovados", recusadoService.buscarTodos());
		return "acompanhamentoLider";
	}

}
