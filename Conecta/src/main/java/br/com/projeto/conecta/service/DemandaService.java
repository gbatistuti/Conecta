package br.com.projeto.conecta.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projeto.conecta.domain.Demanda;
import br.com.projeto.conecta.repository.DemandaRepository;

@Service
public class DemandaService {
	
	@Autowired
	private DemandaRepository demandaRepository;
	
	@Transactional
	public List<Demanda> buscarTodos(){
		return demandaRepository.findAll();
	}
	
	public void excluir(Integer id) {
		demandaRepository.deleteById(id);
	}
	
//	public void editar(Integer id) {
//		demandaRepository.updateById(id);
//	}

}
