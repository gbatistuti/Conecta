package br.com.projeto.conecta.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projeto.conecta.domain.HabilidadesPrincipais;
import br.com.projeto.conecta.repository.HabilidadesPrincipaisRepository;

@Service
public class HabilidadesPrincipaisService {

	@Autowired
	private HabilidadesPrincipaisRepository habilidadesPrincipaisRepository;
	
	@Transactional
	public List<HabilidadesPrincipais> buscarTodos(){
		return habilidadesPrincipaisRepository.findAll();
	}
}
