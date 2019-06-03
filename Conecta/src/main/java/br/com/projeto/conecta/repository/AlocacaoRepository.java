package br.com.projeto.conecta.repository;

import java.time.LocalTime;
import java.util.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.projeto.conecta.domain.Alocacoes;


public interface AlocacaoRepository extends JpaRepository<Alocacoes, Integer> {

	@Query("select max(u.horaFim) from Alocacoes u where u.agendamento.disponivel.idDisponivel = :disponiveis and u.data = :data")
	LocalTime findbyUltimaHora(Date data, Integer disponiveis);
	
}
