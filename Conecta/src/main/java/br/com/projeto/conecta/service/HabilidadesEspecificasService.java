package br.com.projeto.conecta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projeto.conecta.domain.HabilidadeEspecifica;
import br.com.projeto.conecta.repository.HabilidadesEspecificasRepository;

@Service
public class HabilidadesEspecificasService {
	
	@Autowired
	private HabilidadesEspecificasRepository habilidadesEspecificasRepository;
	
	public List<HabilidadeEspecifica> buscarTodos(){
		return habilidadesEspecificasRepository.findAll();
	}
	
}
