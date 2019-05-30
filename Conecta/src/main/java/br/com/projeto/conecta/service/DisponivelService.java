package br.com.projeto.conecta.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projeto.conecta.domain.Consultor;
import br.com.projeto.conecta.domain.Disponiveis;
import br.com.projeto.conecta.repository.DisponivelRepository;
import br.com.projeto.conecta.security.ConectaUserDetailsService;

@Service
public class DisponivelService {

	@Autowired
	private DisponivelRepository disponivelRepository;
	@Autowired
	private ConectaUserDetailsService sessao;
	
	@Transactional
	public List<Disponiveis> buscarTodos(){
		Date data = new Date();
		return disponivelRepository.findByDate(data);
	}

	@Transactional
	public void salvarApontamento(Disponiveis disponiveis) {
		disponivelRepository.save(disponiveis);
	}
	
	@Transactional
	public Disponiveis validaApontamento() {
		Date data = new Date();
		Consultor consultor = sessao.getCurrentConsultor();
		return disponivelRepository.findByUserAndDate(data, consultor);
	}
	
}