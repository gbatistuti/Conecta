package br.com.projeto.conecta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.projeto.conecta.domain.Recusado;

public interface RecusadoRepository extends JpaRepository<Recusado, Integer> {
	
	@Query("select r from Recusado r join fetch r.agendamento a join fetch a.pedido p join fetch p.projeto join fetch a.disponivel d join fetch d.consultor")
	List<Recusado> findAll();
}
