package br.com.projeto.conecta.service;

import java.io.IOException;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import br.com.projeto.conecta.domain.Agendamento;
import br.com.projeto.conecta.domain.Usuario;
import br.com.projeto.conecta.repository.AgendamentoRepository;
import br.com.projeto.conecta.repository.GeradorDeRelatorios;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

@Service
public class AgendamentoService {

	@Autowired
	private AgendamentoRepository agendamentoRepository;
	@Autowired
	private AlocacaoService alocacaoService;
	@Autowired
	private GeradorDeRelatorios geradorDeRelatorios;
	@Autowired
	private ResourceLoader resourceLoader;

	public List<Agendamento> BuscarTodos() {
		return agendamentoRepository.findAll();
	}

	@CacheEvict(value = { "agendamentosPorUsuarioCache", "candidaturasPorUsuarioCache",
			"pedidosFiltradosPorOrigemECandidaturaCache" }, allEntries = true)
	public boolean salvarAgendamento(Agendamento agendamento) {
		agendamentoRepository.save(agendamento);
		return true;
	}

	@Cacheable(value = "candidaturasPorUsuarioCache")
	public List<Agendamento> buscarCandidaturasByUsuario(String email) {
		return agendamentoRepository.findByConsultor(email);
	}

	@Cacheable(value = "agendamentosPorUsuarioCache")
	public List<Agendamento> buscarAgendamentosPorUsuario(Usuario usuario) {
		return agendamentoRepository.findByCliente(usuario);
	}

	public Agendamento getAgendamento(int id) {
		return agendamentoRepository.getOne(id);
	}

	@Cacheable(value = "agendamentosPorStatusCache")
	public List<Agendamento> buscarPorStatus() {
		return agendamentoRepository.findByStatus();
	}
	
	public float buscarCreditosPorHora(Integer integer) {
		return agendamentoRepository.findCreditosPorHora(integer);
	}

	public int buscarHoras(Integer idAgendamento) {
		return agendamentoRepository.findHoras(idAgendamento);
	}

	public float calculaCreditosParaDescontar(Agendamento agendamento) {
		return agendamentoRepository.calcularCreditosPorHoraVezesSugestaoDeHoras(agendamento.getIdAgendamento());
	}

	public Integer buscarProjeto(Agendamento agendamento) {
		return agendamentoRepository.findIdProjeto(agendamento.getIdAgendamento());
	}

	public boolean validaHoras(Agendamento agendamento) {
		LocalTime horaFimAlocacao = alocacaoService.buscaUltimaHoraFimDeAlocacaoDoConsultor(agendamento);
		LocalTime horaFImDisponivel = agendamento.getDisponivel().getHoraFim();

		float horas = Duration.between(horaFimAlocacao, horaFImDisponivel).toMinutes();
		horas /= 60;
		if (horas >= agendamento.getPedido().getHorasContratadas()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean validaCreditos(Agendamento agendamento) {
		float creditosASeremDescontados = agendamento.getPedido().getHorasContratadas()
				* agendamento.getDisponivel().getConsultor().getCreditosPorHora();
		if (creditosASeremDescontados <= agendamento.getPedido().getProjeto().getQtdCreditos()) {
			return true;
		} else {
			return false;
		}
	}
	
	public JasperPrint exportaAgendamentosPorPeriodoPdf(String dataInicio, String dataFim) throws SQLException, JRException, IOException {
		String caminho = resourceLoader.getResource("classpath:/relatorios/agendamentosPorPeriodo.jrxml").getURI().getPath();
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("dataInicio", dataInicio);
		parametros.put("dataFim", dataFim);
		return geradorDeRelatorios.exportJrxml(caminho, parametros);		
	}

	public JasperPrint exportaAgendamentosCriadoPorPdf(String usuario) throws IOException, SQLException, JRException {
		String caminho = resourceLoader.getResource("classpath:/relatorios/agendamentosCriadoPor.jrxml").getURI().getPath();
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("criadoPor", usuario);
		return geradorDeRelatorios.exportJrxml(caminho, parametros);
	}

	public JasperPrint exportaAgendamentosCriadoPorEPeriodoPdf(String usuario, String dataInicio, String dataFim) throws IOException, SQLException, JRException {
		String caminho = resourceLoader.getResource("classpath:/relatorios/agendamentosPorPeriodoECliente.jrxml").getURI().getPath();
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("CriadoPor", usuario);
		parametros.put("dataInicio", dataInicio);
		parametros.put("dataFim", dataFim);
		return geradorDeRelatorios.exportJrxml(caminho, parametros);
	}
	
}