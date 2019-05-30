package br.com.projeto.conecta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import br.com.projeto.conecta.domain.Consultor;
import br.com.projeto.conecta.repository.ConsultorRepository;

@Service
public class ConsultorService {
	
	@Autowired
	private ConsultorRepository consultorRepository;
	
	@Cacheable("consultorCache")
	public List<Consultor> buscarTodos(){
		return consultorRepository.findAll();
	}
}
