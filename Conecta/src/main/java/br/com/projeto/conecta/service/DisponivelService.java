package br.com.projeto.conecta.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import br.com.projeto.conecta.domain.Disponiveis;
import br.com.projeto.conecta.repository.DisponivelRepository;

@Service
public class DisponivelService {

	@Autowired
	private DisponivelRepository disponivelRepository;
	
	@Transactional
	@Cacheable(value = "disponiveisCache")
	public List<Disponiveis> buscarTodos(){
		Date data = new Date();
		return disponivelRepository.findByDate(data);
	}

	@Transactional
	@CacheEvict(value = {"disponiveisCache", "validaApontamentoCache"}, allEntries = true)
	public void salvarApontamento(Disponiveis disponiveis) {
		disponivelRepository.save(disponiveis);
	}
	
	@Transactional
	@Cacheable(value = "validaApontamentoCache")
	public Disponiveis validaApontamento(String email) {
		Date data = new Date();
		return disponivelRepository.findByUserAndDate(data, email);
	}
	
	public Disponiveis getDisponivel(int id) {
		return disponivelRepository.getOne(id);
	}
}