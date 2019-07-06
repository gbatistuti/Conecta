package br.com.projeto.conecta.service;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import br.com.projeto.conecta.domain.Agendamento;
import br.com.projeto.conecta.domain.Alocacao;
import br.com.projeto.conecta.repository.AlocacaoRepository;
import br.com.projeto.conecta.repository.GeradorDeRelatorios;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

@Service
public class AlocacaoService {

	@Autowired
	private AgendamentoService agendamentoService;
	@Autowired
	private ProjetoService projetoService;
	@Autowired
	private AlocacaoRepository alocacaoRepository;
	@Autowired
	private GeradorDeRelatorios geradorDeRelatorios;
	@Autowired
	private ResourceLoader resourceLoader;

	@Transactional
	@Cacheable(value = "alocacoesCache")
	public List<Alocacao> buscarTodos() {
		return alocacaoRepository.findAll();
	}

	@CacheEvict(value = {"agendamentosPorStatusCache", "ultimaHoraFimDeAlocacaoDoConsultorCache", "alocacoesCache", "projetosTodosCache", "projetosCache"}, allEntries = true)
	public void salvarAlocacao(Alocacao alocacao) {
		alocacaoRepository.save(alocacao);
	}

	public void creditosParaDescontar(Agendamento agendamento) {
		float creditosParaDescontar = agendamentoService.calculaCreditosParaDescontar(agendamento);
		projetoService.descontarCreditos(creditosParaDescontar, agendamentoService.buscarProjeto(agendamento));
	}

	@Cacheable(value = "ultimaHoraFimDeAlocacaoDoConsultorCache")
	public LocalTime buscaUltimaHoraFimDeAlocacaoDoConsultor(Agendamento agendamento) {
		LocalDate data = LocalDate.now();
		LocalTime ultimaHora = alocacaoRepository.findbyUltimaHora(data, agendamento.getDisponivel().getIdDisponivel());

		if (ultimaHora == null || ultimaHora.isBefore(LocalTime.now())) {
			int min = LocalTime.now().getMinute();
			if (min >= 0 && min < 30) {
				return LocalTime.of(LocalTime.now().getHour(), 30);
			}
			return LocalTime.of(LocalTime.now().getHour(), 00).plusHours(1);
		}
		return ultimaHora;
	}

	public LocalTime definirHoraFim(LocalTime horaInicio, Agendamento agendamento) {
		LocalTime horaFim;

		if (horaInicio.getMinute() == 30) {
			horaFim = LocalTime.of(horaInicio.getHour(), 30)
					.plusHours(agendamentoService.buscarHoras(agendamento.getIdAgendamento()));
			return horaFim;
		}

		horaFim = LocalTime.of(horaInicio.getHour(), 00)
				.plusHours(agendamentoService.buscarHoras(agendamento.getIdAgendamento()));
		return horaFim;
	}

	public Alocacao getAlocacao(Integer idAlocacao) {
		return alocacaoRepository.getOne(idAlocacao);
	}

	public JasperPrint exportaAlocacoesPorPeriodoPdf(String dataInicio, String dataFim) throws IOException, SQLException, JRException {
		String caminho = resourceLoader.getResource("classpath:/relatorios/alocacoesPorPeriodo.jrxml").getURI().getPath();
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("dataInicio", dataInicio);
		parametros.put("dataFim", dataFim);
		return geradorDeRelatorios.exportJrxml(caminho, parametros);		
	}
}