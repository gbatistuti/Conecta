package br.com.projeto.conecta.service;

import java.time.LocalTime;
import java.util.Calendar;
import java.sql.Date;
import java.util.List;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projeto.conecta.domain.Agendamento;
import br.com.projeto.conecta.domain.Alocacoes;
import br.com.projeto.conecta.repository.AlocacaoRepository;
import br.com.projeto.conecta.security.ConectaUserDetailsService;

@Service
public class AlocacaoService {

	@Autowired
	private AlocacaoRepository alocaoRepository;
	@Autowired
	private ConectaUserDetailsService sessao;

	@Transactional
	public List<Alocacoes> buscarTodos() {
		return alocaoRepository.findAll();
	}

	public void salvarAlocacao(Alocacoes alocacoes) {
		alocaoRepository.save(alocacoes);
	}

	public float CreditosParaDescontar(int sugestaoDeHoras, Float creditosPorHora) {
		return sugestaoDeHoras * creditosPorHora;
	}

	public LocalTime buscaUltimaHora(Agendamento agendamento) {
		Calendar calendar = Calendar.getInstance();
		Date data = new Date(calendar.getTime().getTime());
		data = data.valueOf("2019-05-31");
		Integer d = 3;
		try {
			LocalTime hora = alocaoRepository.findbyUltimaHora(d);
			return hora;
		} catch (NoResultException eX) {
			System.out.println("Nenhum valor encontrado");
			return null;
		}
	}
}