package br.com.projeto.conecta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projeto.conecta.domain.Agendamento;
import br.com.projeto.conecta.domain.Consultor;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Integer> {

	List<Agendamento> findByConsultor(Consultor consultor);

	
	
}