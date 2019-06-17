package br.com.projeto.conecta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import br.com.projeto.conecta.domain.Projeto;
import br.com.projeto.conecta.repository.ProjetoRepository;

@Service
public class ProjetoService {
	
	@Autowired
	private ProjetoRepository projetoRepository;
	
	@Cacheable(value = "projetosTodosCache")
	public Object buscarTodos() {
		return projetoRepository.findAll();
	}
	
	@Cacheable(value = "projetosCache")
	@CacheEvict(value = "idUser")
	public List<Projeto> buscarPor(Integer id) {
		return projetoRepository.getById(id);
	}
	
	@CacheEvict(value = {"projetosTodosCache", "projetosCache"}, allEntries = true)
	public void salvar(Projeto projeto){
		projetoRepository.save(projeto);
	}
	
	public Projeto getProjeto(Integer id) {
		return projetoRepository.getOne(id);
	}
}
