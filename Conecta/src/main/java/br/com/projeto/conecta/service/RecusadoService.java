package br.com.projeto.conecta.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projeto.conecta.domain.Recusado;
import br.com.projeto.conecta.repository.RecusadoRepository;

@Service
public class RecusadoService{

	@Autowired
	private RecusadoRepository recusadoRepository;
	
	@Transactional
	public void salvarRecusado(Recusado recusado) {
		recusadoRepository.save(recusado);
	}

	public List<Recusado> buscarTodos() {
		return recusadoRepository.findAll();
	}
}
