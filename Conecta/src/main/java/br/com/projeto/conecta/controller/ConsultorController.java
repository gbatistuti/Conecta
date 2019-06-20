package br.com.projeto.conecta.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.projeto.conecta.domain.Agendamento;
import br.com.projeto.conecta.domain.Disponiveis;
import br.com.projeto.conecta.domain.Pedido;
import br.com.projeto.conecta.domain.Usuarios;
import br.com.projeto.conecta.repository.GeradorDeRelatorios;
import br.com.projeto.conecta.security.ConectaUserDetailsService;
import br.com.projeto.conecta.service.AgendamentoService;
import br.com.projeto.conecta.service.DisponivelService;
import br.com.projeto.conecta.service.PedidoService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;

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
	@Autowired
	private GeradorDeRelatorios habilidadesPrincipaisRelatorio;
	
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
		
		pedidoService.atualizarPedido(pedido.getIdPedido());
		
		agendamento = new Agendamento(disponivel, usuario, pedido);
		agendamentoService.salvarAgendamento(agendamento);
		return "redirect:/homeConsultor?candidatado";
	}
	
	
	@PostMapping("/exportar")
	public void exportarPdf(ModelAndView model, HttpServletResponse response, String id) throws IOException, JRException, SQLException {
	
		JasperPrint jasperPrint = null;
		
		response.setContentType("application/x-download");
		response.setHeader("content-Disposition", String.format("attachment; filename=\"habilidadesPrincipais.pdf\""));
		
		OutputStream out = response.getOutputStream();
		jasperPrint = habilidadesPrincipaisRelatorio.exportHabilidadesPrincipaisPorId(id);
		JasperExportManager.exportReportToPdfStream(jasperPrint, out);
	}
}