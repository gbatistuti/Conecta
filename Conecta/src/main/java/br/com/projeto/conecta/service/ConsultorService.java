package br.com.projeto.conecta.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.projeto.conecta.domain.Consultor;
import br.com.projeto.conecta.repository.ConsultorRepository;

@Service
public class ConsultorService {
	
	@Autowired
	private ConsultorRepository consultorRepository;
	
	@Transactional
	public List<Consultor> buscarTodos(){
		return consultorRepository.findAll();
	}
}
