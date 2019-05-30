package br.com.projeto.conecta.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.projeto.conecta.domain.Agendamento;
import br.com.projeto.conecta.domain.Alocacoes;
import br.com.projeto.conecta.domain.Disponiveis;
import br.com.projeto.conecta.domain.Usuarios;
import br.com.projeto.conecta.security.ConectaUserDetailsService;
import br.com.projeto.conecta.service.AgendamentoService;
import br.com.projeto.conecta.service.AlocacaoService;
import br.com.projeto.conecta.service.DisponivelService;

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
	
	
	@GetMapping
	public String listarAgendamentos(ModelMap model, HttpServletRequest request) {
		Usuarios usuario = conecta.getCurrentUser();
		model.addAttribute("agendamento", agendamentoService.BuscarPorStatus());
		model.addAttribute("disponiveis", disponivelService.buscarTodos());
		request.setAttribute("nome", usuario.getNome());
		return "homeLider";
	}
	
	@PostMapping("/aprovar")
	public String aprovarAgendamento(Alocacoes alocacoes, Agendamento agendamento, Disponiveis disponivel) {
		alocacoes.setCriadoPor(sessao.getCurrentLider());
		Agendamento agendamentoAlterado = agendamentoService.getAgendamento(agendamento.getIdAgendamento());
		agendamentoAlterado.getPedido().setStatus("aprovado");
		agendamentoService.salvarAgendamento(agendamentoAlterado);
		Disponiveis disponivelAlterado = disponivelService.getDisponivel(disponivel.getIdDisponivel());
		disponivelAlterado.setHoraInicio(disponivel.getHoraInicio());
		disponivelAlterado.setHoraFim(disponivel.getHoraFim());
		disponivelService.salvarApontamento(disponivelAlterado);
		alocacoes.setHoraInicio(horaInicio);
		alocacaoService.salvarAlocacao(alocacoes);
		return "redirect:/homeLider";
	}
	
//	@GetMapping
//	public String ListarDisponiveis(ModelMap model) {
//		model.addAttribute("disponiveis", disponivelService.buscarTodos());
//		return "homeLider";
//	}
	
	
//	@Autowired
//	private AgendamentoService agendamentoService;
//	@Autowired
//	private ProjetoService projetoService;
//	@Autowired
//	private PedidoService pedidoService;
//
//	@GetMapping
//	public String ListarAgendamentos(ModelMap model) {
//		model.addAttribute("agendamento", agendamentoService.BuscarTodos());
//		return "homeLider";
//	}
//	
//	@GetMapping("/gerenciaProjetos")
//	public String listar(ModelMap model) {
//		model.addAttribute("projetos", projetoService.buscarTodos());
//		return "gerenciaProjetos";
//	}
//	
//	@GetMapping("/totinhas")
//	public String ListarDisponiveis(ModelMap model) {
//		model.addAttribute("pedidos", pedidoService.buscarTodos());
//		return "alocacao";
//	}
	
	
}