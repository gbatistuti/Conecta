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
import br.com.projeto.conecta.domain.Projeto;
import br.com.projeto.conecta.domain.Recusado;
import br.com.projeto.conecta.domain.Usuarios;
import br.com.projeto.conecta.security.ConectaUserDetailsService;
import br.com.projeto.conecta.service.AgendamentoService;
import br.com.projeto.conecta.service.AlocacaoService;
import br.com.projeto.conecta.service.DisponivelService;
import br.com.projeto.conecta.service.PedidoService;
import br.com.projeto.conecta.service.ProjetoService;
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
	@Autowired
	private ProjetoService projetoService;

	@GetMapping
	public String listarAgendamentos(ModelMap model, HttpServletRequest request) {
		Usuarios usuario = sessao.getCurrentLider();
		request.setAttribute("nome", usuario.getNome());
		
		model.addAttribute("agendamento", agendamentoService.buscarPorStatus());
		model.addAttribute("disponiveis", disponivelService.buscarTodos());
		return "homeLider";
	}

	@PostMapping("/aprovar")
	@Transactional
	public String aprovarAgendamento(Alocacoes alocacao, Agendamento agendamento) {		
		pedidoService.atualizarStatus("aprovado",agendamento);
		alocacaoService.creditosParaDescontar(agendamento);
		
		LocalTime horaInicio = alocacaoService.buscaUltimaHoraFimDeAlocacaoDoConsultor(agendamento);
		
		alocacao = new Alocacoes(agendamento, sessao.getCurrentLider(), horaInicio, alocacaoService.definirHoraFim(horaInicio, agendamento), alocacao.getMotivo());
		alocacaoService.salvarAlocacao(alocacao);
		return "redirect:/homeLider?aprovado";
	}

	@PostMapping("/reprovar")
	@Transactional
	public String reprovarAgendamento(Recusado recusado, Agendamento agendamento) {
		pedidoService.atualizarStatus("recusado",agendamento);
		Recusado recusadoNovo = new Recusado(recusado.getMotivo(), recusado.getData(), agendamento, sessao.getCurrentLider());
		recusadoService.salvarRecusado(recusadoNovo);
		return "redirect:/homeLider?reprovado";
	}

	@GetMapping("/pedidos")
	public String ListarDisponiveis(ModelMap model, HttpServletRequest request) {
		Usuarios usuario = sessao.getCurrentLider();
		request.setAttribute("nome", usuario.getNome());
		
		model.addAttribute("pedidos", pedidoService.filtrarPorOrigemECandidatura());
		model.addAttribute("disponiveis", disponivelService.buscarTodos());
		return "pedidos";
	}

	@PostMapping("/pedidos/alocar")
	@Transactional
	public String alocarDisponivelAoPedido(Agendamento agendamento, Pedido pedido) {
		agendamento = new Agendamento(agendamento.getDisponivel(), sessao.getCurrentLider(), pedido);
		agendamentoService.salvarAgendamento(agendamento);
		pedidoService.atualizarStatus("aprovado",agendamento);
		alocacaoService.creditosParaDescontar(agendamento);
		
		LocalTime horaInicio = alocacaoService.buscaUltimaHoraFimDeAlocacaoDoConsultor(agendamento);
		
		Alocacoes alocacao = new Alocacoes(agendamento, sessao.getCurrentLider(), horaInicio, alocacaoService.definirHoraFim(horaInicio, agendamento), "Alocação criada a partir da lista de pedidos");
		alocacaoService.salvarAlocacao(alocacao);
		return "redirect:/homeLider/pedidos?sucesso";
	}

	@GetMapping("/alocacoes")
	public String listarAgendamentosAprovadosEReprovados(ModelMap model, HttpServletRequest request) {
		Usuarios usuario = sessao.getCurrentLider();
		request.setAttribute("nome", usuario.getNome());
		
		model.addAttribute("aprovados", alocacaoService.buscarTodos());
		return "agendamentosAprovados";
	}
	
	@GetMapping("/agendamentosReprovados")
	public String listarAgendamentosReprovados(ModelMap model, HttpServletRequest request) {
		Usuarios usuario = sessao.getCurrentLider();
		request.setAttribute("nome", usuario.getNome());
		
		model.addAttribute("reprovados", recusadoService.buscarTodos());
		return "agendamentosReprovados";
	}
	
	@GetMapping("/gerenciaProjetos")
	public String listar(ModelMap model, HttpServletRequest request) {
		Usuarios usuario = sessao.getCurrentLider();
		request.setAttribute("nome", usuario.getNome());
		
		model.addAttribute("projetos", projetoService.buscarTodos());
		return "gerenciaProjetos";
	}
	
	@PostMapping("gerenciaProjetos/atualizar")
	@Transactional
	public String atualizarQtdDeCreditos(Projeto projeto) {
		projetoService.atualizarCreditos(projeto.getQtdCreditos(), projeto.getIdProjeto());
		return "redirect:/homeLider/gerenciaProjetos?atualizado";
	}

}