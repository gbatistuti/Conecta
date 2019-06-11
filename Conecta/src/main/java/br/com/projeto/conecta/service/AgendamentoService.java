package br.com.projeto.conecta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import br.com.projeto.conecta.domain.Agendamento;
import br.com.projeto.conecta.domain.Usuarios;
import br.com.projeto.conecta.repository.AgendamentoRepository;

@Service
public class AgendamentoService {

	@Autowired
	private AgendamentoRepository agendamentoRepository;

	public List<Agendamento> BuscarTodos() {
		return agendamentoRepository.findAll();
	}

	@CacheEvict(value = {"agendamentosPorUsuarioCache", "candidaturasPorUsuarioCache", "pedidosFiltradosPorOrigemECandidaturaCache"}, allEntries = true)
	public boolean salvarAgendamento(Agendamento agendamento) {
		agendamentoRepository.save(agendamento);
		return true;
	}

	@Cacheable(value = "candidaturasPorUsuarioCache")
	public List<Agendamento> buscarCandidaturasByUsuario(String email) {
		return agendamentoRepository.findByConsultor(email);
	}

	@Cacheable(value = "agendamentosPorUsuarioCache")
	public List<Agendamento> buscarAgendamentosPorUsuario(Usuarios usuario) {
		return agendamentoRepository.findByCliente(usuario);
	}

	public Agendamento getAgendamento(int id) {
		return agendamentoRepository.getOne(id);
	}

	@Cacheable(value = "agendamentosPorStatusCache")
	public List<Agendamento> buscarPorStatus() {
		return agendamentoRepository.findByStatus();
	}
}