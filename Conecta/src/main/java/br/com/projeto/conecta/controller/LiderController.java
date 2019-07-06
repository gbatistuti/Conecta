package br.com.projeto.conecta.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.time.LocalTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;



import br.com.projeto.conecta.domain.Agendamento;
import br.com.projeto.conecta.domain.Alocacao;
import br.com.projeto.conecta.domain.Pedido;
import br.com.projeto.conecta.domain.Projeto;
import br.com.projeto.conecta.domain.Reprovado;
import br.com.projeto.conecta.domain.Usuario;
import br.com.projeto.conecta.security.ConectaUserDetailsService;
import br.com.projeto.conecta.service.AgendamentoService;
import br.com.projeto.conecta.service.AlocacaoService;
import br.com.projeto.conecta.service.DisponivelService;
import br.com.projeto.conecta.service.MensagemService;
import br.com.projeto.conecta.service.PedidoService;
import br.com.projeto.conecta.service.ProjetoService;
import br.com.projeto.conecta.service.RecusadoService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;

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
	@Autowired
	private MensagemService mensagemService;
	

	@GetMapping
	public String listarAgendamentos(ModelMap model, HttpServletRequest request) {
		Usuario usuario = sessao.getCurrentLider();
		request.setAttribute("nome", usuario.getNome());
		
		model.addAttribute("agendamento", agendamentoService.buscarPorStatus());
		model.addAttribute("disponiveis", disponivelService.buscarTodos());
		return "homeLider";
	}

	@PostMapping("/aprovar")
	@Transactional
	public String aprovarAgendamento(Alocacao alocacao, Agendamento agendamento) {		
		pedidoService.atualizarStatus("Aprovado",agendamento);
		alocacaoService.creditosParaDescontar(agendamento);
		
		LocalTime horaInicio = alocacaoService.buscaUltimaHoraFimDeAlocacaoDoConsultor(agendamento);
		
		alocacao = new Alocacao(agendamento, sessao.getCurrentLider(), horaInicio, alocacaoService.definirHoraFim(horaInicio, agendamento), alocacao.getMotivo());
		alocacaoService.salvarAlocacao(alocacao);

		mensagemService.emailAprovacaoConsultor(alocacao.getIdAlocacao(), agendamento.getPedido().getIdPedido());
		mensagemService.emailAprovacaoLider(alocacao.getIdAlocacao(), agendamento.getPedido().getIdPedido());
		
		return "redirect:/homeLider?aprovado";
	}

	@PostMapping("/reprovar")
	@Transactional
	public String reprovarAgendamento(Reprovado recusado, Agendamento agendamento) {
		pedidoService.atualizarStatus("Recusado",agendamento);
		Reprovado recusadoNovo = new Reprovado(recusado.getMotivo(), agendamento, sessao.getCurrentLider());
		recusadoService.salvarRecusado(recusadoNovo);

		mensagemService.emailReprovacaoLider(agendamento.getIdAgendamento(), sessao.getCurrentLider(), recusado);
		mensagemService.emailReprovacaoCliente(recusado);
		
		return "redirect:/homeLider?reprovado";
	}

	@GetMapping("/pedidos")
	public String ListarDisponiveis(ModelMap model, HttpServletRequest request) {
		Usuario usuario = sessao.getCurrentLider();
		request.setAttribute("nome", usuario.getNome());
		
		model.addAttribute("pedidos", pedidoService.filtrarPorOrigemECandidatura());
		model.addAttribute("disponiveis", disponivelService.buscarTodos());
		return "pedidos";
	}

	@PostMapping("/pedidos/alocar")
	@Transactional
	public String alocarDisponivelAoPedido(Agendamento agendamento, Pedido pedido, Alocacao alocacao) {
		agendamento.setPedido(pedido);
		agendamento = new Agendamento(agendamento.getDisponivel(), sessao.getCurrentLider(), agendamento.getPedido());
		agendamentoService.salvarAgendamento(agendamento);
		pedidoService.atualizarStatus("Aprovado",agendamento);
		alocacaoService.creditosParaDescontar(agendamento);
		
		LocalTime horaInicio = alocacaoService.buscaUltimaHoraFimDeAlocacaoDoConsultor(agendamento);
		
		alocacao = new Alocacao(agendamento, sessao.getCurrentLider(), horaInicio, alocacaoService.definirHoraFim(horaInicio, agendamento), "Alocação criada a partir da lista de pedidos");
		alocacaoService.salvarAlocacao(alocacao);
		
		mensagemService.emailAprovacaoConsultor(alocacao.getIdAlocacao(), pedido.getIdPedido());
		mensagemService.emailAprovacaoLider(alocacao.getIdAlocacao(), pedido.getIdPedido());
		
		return "redirect:/homeLider/pedidos?sucesso";
	}

	@GetMapping("/alocacoes")
	public String listarAgendamentosAprovadosEReprovados(ModelMap model, HttpServletRequest request) {
		Usuario usuario = sessao.getCurrentLider();
		request.setAttribute("nome", usuario.getNome());
		
		model.addAttribute("aprovados", alocacaoService.buscarTodos());
		return "agendamentosAprovados";
	}
	
	@GetMapping("/agendamentosReprovados")
	public String listarAgendamentosReprovados(ModelMap model, HttpServletRequest request) {
		Usuario usuario = sessao.getCurrentLider();
		request.setAttribute("nome", usuario.getNome());
		
		model.addAttribute("reprovados", recusadoService.buscarTodos());
		return "agendamentosReprovados";
	}
	
	@GetMapping("/gerenciaProjetos")
	public String listar(ModelMap model, HttpServletRequest request) {
		Usuario usuario = sessao.getCurrentLider();
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
	
	@GetMapping("/relatorios")
	public String relatorios(HttpServletRequest request) {
		Usuario usuario = sessao.getCurrentLider();
		request.setAttribute("nome", usuario.getNome());
		
		return "relatorios";
	}
	
	@PostMapping("/relatorios/gerarDisponiveisPorPeriodo")
	public void gerarPdfDisponiveisPorPeriodo(ModelAndView model, HttpServletResponse response, String dataInicio, String dataFim) throws IOException, JRException, SQLException {
		response.setContentType("application/x-download");
		response.setHeader("content-Disposition", String.format("attachment; filename=\"Disponiveis.pdf\""));
		OutputStream out = response.getOutputStream();
		JasperPrint jasperPrint = disponivelService.exportaDisponiveisPorPeriodoPdf(dataInicio, dataFim);
		JasperExportManager.exportReportToPdfStream(jasperPrint, out);
	}
	@PostMapping("/relatorios/gerarAgendamentosPorPeriodo")
	public void gerarPdfAgendamentosPorPeriodo(ModelAndView model, HttpServletResponse response, String dataInicio, String dataFim) throws IOException, JRException, SQLException {
		response.setContentType("application/x-download");
		response.setHeader("content-Disposition", String.format("attachment; filename=\"Agendamentos.pdf\""));
		OutputStream out = response.getOutputStream();
		JasperPrint jasperPrint = agendamentoService.exportaAgendamentosPorPeriodoPdf(dataInicio, dataFim);
		JasperExportManager.exportReportToPdfStream(jasperPrint, out);
	}
	@PostMapping("/relatorios/gerarAlocacoesPorPeriodo")
	public void gerarPdfAlocacoesPorPeriodo(ModelAndView model, HttpServletResponse response, String dataInicio, String dataFim) throws IOException, JRException, SQLException {
		response.setContentType("application/x-download");
		response.setHeader("content-Disposition", String.format("attachment; filename=\"AlocaçõesPorPeríodo.pdf\""));
		OutputStream out = response.getOutputStream();
		JasperPrint jasperPrint = alocacaoService.exportaAlocacoesPorPeriodoPdf(dataInicio, dataFim);
		JasperExportManager.exportReportToPdfStream(jasperPrint, out);
	}
	@PostMapping("/relatorios/gerarAlocacoesPorProjeto")
	public void gerarPdfAlocacoesPorProjeto(ModelAndView model, HttpServletResponse response, String projeto) throws IOException, JRException, SQLException {
		response.setContentType("application/x-download");
		response.setHeader("content-Disposition", String.format("attachment; filename=\"AlocaçõesPorProjeto.pdf\""));
		OutputStream out = response.getOutputStream();
		JasperPrint jasperPrint = projetoService.exportaAlocacoesPorProjetoPdf(projeto);
		JasperExportManager.exportReportToPdfStream(jasperPrint, out);
	}
	
	@PostMapping("/relatorios/gerarDisponiveisPorConsultor")
	public void gerarPdfDisponiveisPorConsultor(ModelAndView model, HttpServletResponse response, String consultor) throws IOException, JRException, SQLException {
		response.setContentType("application/x-download");
		response.setHeader("content-Disposition", String.format("attachment; filename=\"DisponiveisPorConsultor.pdf\""));
		OutputStream out = response.getOutputStream();
		JasperPrint jasperPrint = disponivelService.exportaDisponiveisPorConsultorPdf(consultor);
		JasperExportManager.exportReportToPdfStream(jasperPrint, out);
	}
	@PostMapping("/relatorios/gerarAgendamentosCriadoPor")
	public void gerarPdfAgendamentosCriadoPor(ModelAndView model, HttpServletResponse response, String usuario) throws IOException, JRException, SQLException {
		response.setContentType("application/x-download");
		response.setHeader("content-Disposition", String.format("attachment; filename=\"AgendamentosPorUsuario.pdf\""));
		OutputStream out = response.getOutputStream();
		JasperPrint jasperPrint = agendamentoService.exportaAgendamentosCriadoPorPdf(usuario);
		JasperExportManager.exportReportToPdfStream(jasperPrint, out);
	}
	
	@PostMapping("/relatorios/gerarDisponiveisPorPeridoEConsultor")
	public void gerarPdfDisponiveisPorPeriodoEConsultor(ModelAndView model, HttpServletResponse response, String consultor, String dataInicio, String dataFim) throws IOException, JRException, SQLException {
		response.setContentType("application/x-download");
		response.setHeader("content-Disposition", String.format("attachment; filename=\"DisponiveisPorConsultorEPeriodo.pdf\""));
		OutputStream out = response.getOutputStream();
		JasperPrint jasperPrint = disponivelService.exportaDisponiveisPorConsultorEPeriodoPdf(consultor, dataInicio, dataFim);
		JasperExportManager.exportReportToPdfStream(jasperPrint, out);
	}
	@PostMapping("/relatorios/gerarAgendamentosPorPeriodoECliente")
	public void gerarPdfAgendamentosPorPeriodoECliente(ModelAndView model, HttpServletResponse response, String usuario, String dataInicio, String dataFim ) throws IOException, JRException, SQLException {
		response.setContentType("application/x-download");
		response.setHeader("content-Disposition", String.format("attachment; filename=\"AgendamentosPorUsuarioEPeriodo.pdf\""));
		OutputStream out = response.getOutputStream();
		JasperPrint jasperPrint = agendamentoService.exportaAgendamentosCriadoPorEPeriodoPdf(usuario, dataInicio, dataFim);
		JasperExportManager.exportReportToPdfStream(jasperPrint, out);
	}
}