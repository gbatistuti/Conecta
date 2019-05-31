package br.com.projeto.conecta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projeto.conecta.domain.Projeto;
import br.com.projeto.conecta.repository.ProjetoRepository;

@Service
public class ProjetoService {
	
	@Autowired
	private ProjetoRepository projetoRepository;
	
	public Object buscarTodos() {
		return projetoRepository.findAll();
	}
	
	public List<Projeto> buscarPor(Integer id) {
		return projetoRepository.getById(id);
	}
}
