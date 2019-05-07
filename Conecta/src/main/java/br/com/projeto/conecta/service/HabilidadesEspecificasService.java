package br.com.projeto.conecta.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projeto.conecta.domain.HabilidadesEspecificas;
import br.com.projeto.conecta.repository.HabilidadesEspecificasRepository;

@Service
public class HabilidadesEspecificasService {
	
	@Autowired
	private HabilidadesEspecificasRepository habilidadesEspecificasRepository;
	
	@Transactional
	public List<HabilidadesEspecificas> buscarTodos(){
		return habilidadesEspecificasRepository.findAll();
	}
	

}
