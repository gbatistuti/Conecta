package br.com.projeto.conecta.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public void salvarAlocacao(Alocacoes alocacoes) {
		alocaoRepository.save(alocacoes);
	}
}