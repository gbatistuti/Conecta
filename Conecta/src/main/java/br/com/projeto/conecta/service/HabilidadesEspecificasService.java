package br.com.projeto.conecta.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projeto.conecta.domain.Disponiveis;
import br.com.projeto.conecta.domain.HabilidadesEspecificas;
import br.com.projeto.conecta.repository.HabilidadesEspecificasRepository;

@Service
public class HabilidadesEspecificasService {
	
	@Autowired
	private HabilidadesEspecificasRepository habilidadesEspecificasRepository;
	
	@Autowired
	private DisponivelService disponivelService;
	
	private List<Disponiveis> listaDisponiveis = new ArrayList();
	
	private List<String> listaNomeHabilidadesEspecificas = new ArrayList();
	
	public List<HabilidadesEspecificas> buscarTodos(){
		return habilidadesEspecificasRepository.findAll();
	}
	
}
