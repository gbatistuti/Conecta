package br.com.projeto.conecta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projeto.conecta.domain.HabilidadeEspecifica;
import br.com.projeto.conecta.repository.HabilidadeEspecificaRepository;

@Service
public class HabilidadesEspecificasService {
	
	@Autowired
	private HabilidadeEspecificaRepository habilidadesEspecificasRepository;
	
	public List<HabilidadeEspecifica> buscarTodos(){
		return habilidadesEspecificasRepository.findAll();
	}
	
}
