package br.com.projeto.conecta.service;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

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
	
//	public LocalTime buscaUltimaHora(Agendamento agendamento) {
//		Date data = new Date();
//		return alocaoRepository.findbyUltimaHora(data, agendamento.getDisponivel());
//	}
}