package br.com.projeto.conecta.service;

<<<<<<< HEAD
import java.time.LocalTime;
import java.util.Date;
=======
import java.sql.Date;
import java.time.LocalTime;
import java.util.Calendar;
>>>>>>> 9c493f4a13f984ca6b87942f7d0965206687b10d
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projeto.conecta.domain.Agendamento;
import br.com.projeto.conecta.domain.Alocacoes;
import br.com.projeto.conecta.repository.AlocacaoRepository;
<<<<<<< HEAD
import br.com.projeto.conecta.security.ConectaUserDetailsService;
=======
>>>>>>> 9c493f4a13f984ca6b87942f7d0965206687b10d

@Service
public class AlocacaoService {

	@Autowired
	private AlocacaoRepository alocaoRepository;
<<<<<<< HEAD
	@Autowired
	private ConectaUserDetailsService sessao;
=======
>>>>>>> 9c493f4a13f984ca6b87942f7d0965206687b10d

	@Transactional
	public List<Alocacoes> buscarTodos() {
		return alocaoRepository.findAll();
	}

<<<<<<< HEAD
	public void salvarAlocacao(Alocacoes alocacoes) {
		alocaoRepository.save(alocacoes);
	}

	public float CreditosParaDescontar(int sugestaoDeHoras, Float creditosPorHora) {
		return sugestaoDeHoras * creditosPorHora;
	}
	
//	public LocalTime buscaUltimaHora(Agendamento agendamento) {
//		Date data = new Date();
//		return alocaoRepository.findbyUltimaHora(data, agendamento.getDisponivel());
//	}
=======
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
>>>>>>> 9c493f4a13f984ca6b87942f7d0965206687b10d
}