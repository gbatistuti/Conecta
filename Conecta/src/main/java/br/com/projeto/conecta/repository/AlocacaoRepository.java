package br.com.projeto.conecta.repository;

import java.time.LocalTime;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.projeto.conecta.domain.Alocacoes;
import br.com.projeto.conecta.domain.Disponiveis;

public interface AlocacaoRepository extends JpaRepository<Alocacoes, Integer> {

	@Query("select max(u.horaFim) from Alocacoes u where u.data = :data and u.agendamento.disponiveis = :disponiveis ")
	LocalTime findbyUltimaHora(Date data, Disponiveis disponiveis);
	
	
	
}
