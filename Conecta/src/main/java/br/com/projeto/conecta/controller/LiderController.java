package br.com.projeto.conecta.controller;

import java.time.LocalTime;

import javax.servlet.http.HttpServletRequest;

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
	public String aprovarAgendamento(Alocacoes alocacoes, Agendamento agendamento) {
		
		Authentication auth = authenticationFacade.getAuthentication();
		auth.getName();
		

		alocacoes.setCriadoPor(sessao.getCurrentLider());

		Agendamento agendamentoAlterado = agendamentoService.getAgendamento(agendamento.getIdAgendamento());
		agendamentoAlterado.getPedido().setStatus("aprovado");

		float creditosDoProjeto = agendamentoAlterado.getPedido().getProjeto().getQtdCreditos();
		float creditosParaDescontar = alocacaoService.CreditosParaDescontar(
				agendamentoAlterado.getPedido().getSugestaoDeHoras(),
				agendamentoAlterado.getDisponivel().getConsultor().getCreditosPorHora());

		agendamentoAlterado.getPedido().getProjeto().setQtdCreditos(creditosDoProjeto - creditosParaDescontar);

		LocalTime horaInicio = alocacaoService.buscaUltimaHora(agendamentoAlterado);

		if (horaInicio == null) {
			alocacoes.setHoraInicio(LocalTime.now());
		} else {
			alocacoes.setHoraInicio(horaInicio);
		}

		LocalTime horaFim = LocalTime.of(horaInicio.getHour(), 0)
				.plusHours(alocacoes.getAgendamento().getPedido().getSugestaoDeHoras());

		alocacoes.setHoraFim(horaFim);

		agendamentoService.salvarAgendamento(agendamentoAlterado);
		alocacaoService.salvarAlocacao(alocacoes);
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