package br.com.projeto.conecta.service;

import java.sql.Date;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projeto.conecta.domain.Agendamento;
import br.com.projeto.conecta.domain.Alocacoes;
import br.com.projeto.conecta.repository.AlocacaoRepository;

@Service
public class AlocacaoService {

	@Autowired
	private AlocacaoRepository alocaoRepository;

	@Transactional
	public List<Alocacoes> buscarTodos() {
		return alocaoRepository.findAll();
	}

	public void salvarAlocacao(Alocacoes alocacao) {
		alocaoRepository.save(alocacao);
	}

	public float creditosParaDescontar(int sugestaoDeHoras, float creditosPorHora) {
		return sugestaoDeHoras * creditosPorHora;
	}

	public LocalTime buscaUltimaHora(Agendamento agendamento) {
		Calendar calendar = Calendar.getInstance();
		Date data = new Date(calendar.getTime().getTime());
		LocalTime hora = alocaoRepository.findbyUltimaHora(data, agendamento.getDisponivel().getIdDisponivel());

		if (hora == null) {

			int min = LocalTime.now().getMinute();

			if (min >= 0 && min < 30) {
				return LocalTime.of(LocalTime.now().getHour(), 30);
			}

			return LocalTime.of(LocalTime.now().getHour(), 00).plusHours(1);
		}

		return hora;
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