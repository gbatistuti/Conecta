package br.com.projeto.conecta.repository;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.projeto.conecta.domain.Alocacoes;


public interface AlocacaoRepository extends JpaRepository<Alocacoes, Integer> {

	@Query("select a from Alocacoes a join fetch a.agendamento u join fetch u.pedido p join fetch p.projeto join fetch u.disponivel d join fetch d.consultor join fetch a.criadoPor")
	List<Alocacoes> findAll();
	
	@Query("select max(u.horaFim) from Alocacoes u where u.agendamento.disponivel.idDisponivel = :disponiveis and u.data = :data")
	LocalTime findbyUltimaHora(Date data, Integer disponiveis);
	
}
