package br.com.projeto.conecta.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projeto.conecta.domain.Disponiveis;
import br.com.projeto.conecta.repository.DisponivelRepository;

@Service
public class DisponivelService {

	@Autowired
	private DisponivelRepository disponivelRepository;
	
	@Transactional
	public List<Disponiveis> buscarTodos(){
		Date data = new Date();
		return disponivelRepository.findByDate(data);
	}
	
}