package br.com.projeto.conecta.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projeto.conecta.domain.Disponiveis;
import br.com.projeto.conecta.domain.HabilidadesEspecificas;
import br.com.projeto.conecta.repository.DisponivelRepository;

@Service
public class DisponivelService {

	@Autowired
	private DisponivelRepository disponivelRepository;
	
	@Transactional
	public List<Disponiveis> buscarTodos(){
		return disponivelRepository.findAll();
	}
	
}