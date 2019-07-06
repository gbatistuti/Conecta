package br.com.projeto.conecta.service;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import br.com.projeto.conecta.domain.Agendamento;
import br.com.projeto.conecta.domain.Usuario;
import br.com.projeto.conecta.repository.AgendamentoRepository;

@Service
public class AgendamentoService {

	@Autowired
	private AgendamentoRepository agendamentoRepository;
	@Autowired
	private AlocacaoService alocacaoService;

	public List<Agendamento> BuscarTodos() {
		return agendamentoRepository.findAll();
	}

	@CacheEvict(value = { "agendamentosPorUsuarioCache", "candidaturasPorUsuarioCache",
			"pedidosFiltradosPorOrigemECandidaturaCache" }, allEntries = true)
	public boolean salvarAgendamento(Agendamento agendamento) {
		agendamentoRepository.save(agendamento);
		return true;
	}

	@Cacheable(value = "candidaturasPorUsuarioCache")
	public List<Agendamento> buscarCandidaturasByUsuario(String email) {
		return agendamentoRepository.findByConsultor(email);
	}

	@Cacheable(value = "agendamentosPorUsuarioCache")
	public List<Agendamento> buscarAgendamentosPorUsuario(Usuario usuario) {
		return agendamentoRepository.findByCliente(usuario);
	}

	public Agendamento getAgendamento(int id) {
		return agendamentoRepository.getOne(id);
	}

	@Cacheable(value = "agendamentosPorStatusCache")
	public List<Agendamento> buscarPorStatus() {
		return agendamentoRepository.findByStatus();
	}
	
	public float buscarCreditosPorHora(Integer integer) {
		return agendamentoRepository.findCreditosPorHora(integer);
	}

	public int buscarHoras(Integer idAgendamento) {
		return agendamentoRepository.findHoras(idAgendamento);
	}

	public float calculaCreditosParaDescontar(Agendamento agendamento) {
		return agendamentoRepository.calcularCreditosPorHoraVezesSugestaoDeHoras(agendamento.getIdAgendamento());
	}

	public Integer buscarProjeto(Agendamento agendamento) {
		return agendamentoRepository.findIdProjeto(agendamento.getIdAgendamento());
	}

	public boolean validaHoras(Agendamento agendamento) {
		LocalTime horaFimAlocacao = alocacaoService.buscaUltimaHoraFimDeAlocacaoDoConsultor(agendamento);
		LocalTime horaFImDisponivel = agendamento.getDisponivel().getHoraFim();

		float horas = Duration.between(horaFimAlocacao, horaFImDisponivel).toMinutes();
		horas /= 60;
		if (horas >= agendamento.getPedido().getHorasContratadas()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean validaCreditos(Agendamento agendamento) {
		float creditosASeremDescontados = agendamento.getPedido().getHorasContratadas()
				* agendamento.getDisponivel().getConsultor().getCreditosPorHora();
		if (creditosASeremDescontados <= agendamento.getPedido().getProjeto().getQtdCreditos()) {
			return true;
		} else {
			return false;
		}
	}
}