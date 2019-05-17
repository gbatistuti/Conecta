package br.com.projeto.conecta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.projeto.conecta.domain.Agendamento;
import br.com.projeto.conecta.repository.AgendamentoRepository;


@Service
public class AgendamentoService {

	@Autowired
	private AgendamentoRepository agendamentoRepository;

	public List<Agendamento> BuscarTodos() {
		return agendamentoRepository.findAll();
	}
	
	public boolean salvarAgendamento(Agendamento agendamento) {
		agendamentoRepository.save(agendamento);
		return true;
	}
}
