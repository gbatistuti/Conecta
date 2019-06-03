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
import br.com.projeto.conecta.domain.Recusado;
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
		//Usuarios usuario = sessao.getCurrentUser();
		//request.setAttribute("nome", usuario.getNome());
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

		agendamentoService.salvarAgendamento(agendamento);
		alocacao = new Alocacoes(agendamento, sessao.getCurrentLider(), horaInicio, alocacaoService.definirHoraFim(horaInicio, alocacao));
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
	public String listarDisponiveis(ModelMap model) {
		model.addAttribute("pedidos", pedidoService.buscarPorStatus());
		return "alocacao";
	}
	
	@GetMapping("/acompanhamento")
	public String listarAgendamentosAprovadosEReprovados(ModelMap model) {
		model.addAttribute("aprovados", alocacaoService.buscarTodos());
		model.addAttribute("reprovados", recusadoService.buscarTodos());
		return "acompanhamentoLider";
	}

}