package br.com.projeto.conecta.controller;

import java.time.LocalTime;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.projeto.conecta.domain.Agendamento;
import br.com.projeto.conecta.domain.Alocacoes;
import br.com.projeto.conecta.domain.Recusado;
import br.com.projeto.conecta.domain.Usuarios;
import br.com.projeto.conecta.security.AuthenticationFacade;
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
	private ConectaUserDetailsService conecta;
	@Autowired
	private AgendamentoService agendamentoService;
	@Autowired
	private DisponivelService disponivelService;
	@Autowired
	private AlocacaoService alocacaoService;
	@Autowired
	private ConectaUserDetailsService sessao;
	@Autowired
	private RecusadoService recusadoService;
	@Autowired
	private PedidoService pedidoService;
	@Autowired
	private AuthenticationFacade authenticationFacade;

	@GetMapping
	public String listarAgendamentos(ModelMap model, HttpServletRequest request) {
		Usuarios usuario = conecta.getCurrentUser();
		model.addAttribute("agendamento", agendamentoService.BuscarPorStatus());
		model.addAttribute("disponiveis", disponivelService.buscarTodos());
		request.setAttribute("nome", usuario.getNome());
		return "homeLider";
	}

	
	@PostMapping("/aprovar")
	@Transactional
	public String aprovarAgendamento(Alocacoes alocacao, Agendamento agendamento) {

		Authentication auth = authenticationFacade.getAuthentication();
		auth.getName();

		alocacao.setCriadoPor(sessao.getCurrentLider());

		Agendamento agendamentoAlterado = agendamentoService.getAgendamento(agendamento.getIdAgendamento());

		agendamentoAlterado.getPedido().setStatus("aprovado");

		float creditosDoProjeto = agendamentoAlterado.getPedido().getProjeto().getQtdCreditos();
		float creditosParaDescontar = alocacaoService.creditosParaDescontar(
				agendamentoAlterado.getPedido().getSugestaoDeHoras(),
				agendamentoAlterado.getDisponivel().getConsultor().getCreditosPorHora());

		agendamentoAlterado.getPedido().getProjeto().setQtdCreditos(creditosDoProjeto - creditosParaDescontar);

		LocalTime horaInicio = alocacaoService.buscaUltimaHora(agendamentoAlterado);

		alocacao.setHoraInicio(horaInicio);
		
		alocacao.setHoraFim(alocacaoService.definirHoraFim(horaInicio, alocacao));

		agendamentoService.salvarAgendamento(agendamentoAlterado);
		alocacaoService.salvarAlocacao(alocacao);
		return "redirect:/homeLider";
	}

	@PostMapping("/reprovar")
	public String reprovarAgendamento(Recusado recusado, Agendamento agendamento) {
		recusado.setCriadoPor(sessao.getCurrentUser());
		recusadoService.salvarRecusado(recusado);
		Agendamento agendamentoAlterado = agendamentoService.getAgendamento(agendamento.getIdAgendamento());
		agendamentoAlterado.getPedido().setStatus("recusado");
		agendamentoService.salvarAgendamento(agendamentoAlterado);
		return "redirect:/homeLider";

	}

	@GetMapping("/alocacao")
	public String ListarDisponiveis(ModelMap model) {
		model.addAttribute("pedidos", pedidoService.buscarPorStatus());
		return "alocacao";
	}

}