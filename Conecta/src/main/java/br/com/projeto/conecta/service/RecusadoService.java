package br.com.projeto.conecta.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import br.com.projeto.conecta.domain.Reprovado;
import br.com.projeto.conecta.repository.ReprovadoRepository;

@Service
public class RecusadoService{

	@Autowired
	private ReprovadoRepository recusadoRepository;
	
	@Transactional
	@CacheEvict(value = {"agendamentosPorStatusCache", "reprovacoesCache", "candidaturasPorUsuarioCache"}, allEntries = true)
	public void salvarRecusado(Reprovado recusado) {
		recusadoRepository.save(recusado);
	}

	@Cacheable(value = "reprovacoesCache")
	public List<Reprovado> buscarTodos() {
		return recusadoRepository.findAll();
	}
}
