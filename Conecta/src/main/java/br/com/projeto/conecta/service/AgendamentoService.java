package br.com.projeto.conecta.service;

import java.sql.Date;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import br.com.projeto.conecta.domain.Agendamento;
import br.com.projeto.conecta.domain.Usuarios;
import br.com.projeto.conecta.repository.AgendamentoRepository;
import br.com.projeto.conecta.repository.AlocacaoRepository;
import br.com.projeto.conecta.repository.DisponivelRepository;


@Service
public class AgendamentoService {

	@Autowired
	private AgendamentoRepository agendamentoRepository;
	@Autowired
	private AlocacaoRepository alocacaoRepository;
	@Autowired
	private DisponivelRepository disponivelRepository;

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
	
	public Boolean buscaUltimaHora(Agendamento agendamento) {
		
		Calendar calendar = Calendar.getInstance();
		Date data = new Date(calendar.getTime().getTime());
		LocalTime ultimaHoraAlocacao = alocacaoRepository.findbyUltimaHora(data, agendamento.getDisponivel().getIdDisponivel());
		
		if (ultimaHoraAlocacao == null || ultimaHoraAlocacao.isBefore(LocalTime.now())) {
			ultimaHoraAlocacao = LocalTime.of(0, 0);
		}
		LocalTime ultimaHoraDisponivel = dispo
		
	}
}