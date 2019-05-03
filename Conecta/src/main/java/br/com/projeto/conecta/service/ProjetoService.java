package br.com.projeto.conecta.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projeto.conecta.domain.Demanda;
import br.com.projeto.conecta.domain.Projeto;
import br.com.projeto.conecta.repository.ProjetoRepository;

@Service
public class ProjetoService {
	
	@Autowired
	private ProjetoRepository projetoRepository;
	
	@Transactional
	public List<Projeto> buscarTodos(){
		return projetoRepository.findAll();
	}
	
	public void excluir(Integer id) {
		projetoRepository.deleteById(id);
	}
	
	public void editar(Integer id) {
		projetoRepository.updateById(id);
	}

}
