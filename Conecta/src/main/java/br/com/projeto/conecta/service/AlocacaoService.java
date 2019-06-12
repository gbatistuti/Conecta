package br.com.projeto.conecta.service;

import java.sql.Date;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import br.com.projeto.conecta.domain.Agendamento;
import br.com.projeto.conecta.domain.Alocacoes;
import br.com.projeto.conecta.repository.AlocacaoRepository;

@Service
public class AlocacaoService {

	@Autowired
	private AlocacaoRepository alocacaoRepository;

	@Transactional
	@Cacheable(value = "alocacoesCache")
	public List<Alocacoes> buscarTodos() {
		return alocacaoRepository.findAll();
	}

	@CacheEvict(value = {"agendamentosPorStatusCache", "ultimaHoraFimDeAlocacaoDoConsultorCache", "alocacoesCache", "projetosTodosCache", "projetosCache"}, allEntries = true)
	public void salvarAlocacao(Alocacoes alocacao) {
		alocacaoRepository.save(alocacao);
	}

	public float creditosParaDescontar(Agendamento agendamento) {
		float creditosPorHora = agendamento.getDisponivel().getConsultor().getCreditosPorHora();
		int horasConstratadas = agendamento.getPedido().getSugestaoDeHoras();
		float creditosDoProjeto = agendamento.getPedido().getProjeto().getQtdCreditos();

		float creditosParaDescontar = (creditosPorHora * horasConstratadas);

		return creditosDoProjeto - creditosParaDescontar;
	}

	@Cacheable(value = "ultimaHoraFimDeAlocacaoDoConsultorCache")
	public LocalTime buscaUltimaHoraFimDeAlocacaoDoConsultor(Agendamento agendamento) {
		Calendar calendar = Calendar.getInstance();
		Date data = new Date(calendar.getTime().getTime());
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

	public LocalTime definirHoraFim(LocalTime horaInicio, Alocacoes alocacao) {
		LocalTime horaFim;

		if (horaInicio.getMinute() == 30) {
			horaFim = LocalTime.of(horaInicio.getHour(), 30)
					.plusHours(alocacao.getAgendamento().getPedido().getSugestaoDeHoras());
			return horaFim;
		}

		horaFim = LocalTime.of(horaInicio.getHour(), 00)
				.plusHours(alocacao.getAgendamento().getPedido().getSugestaoDeHoras());
		return horaFim;
	}
}