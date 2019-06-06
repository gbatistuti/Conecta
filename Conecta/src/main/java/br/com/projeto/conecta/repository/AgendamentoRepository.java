package br.com.projeto.conecta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.projeto.conecta.domain.Agendamento;
import br.com.projeto.conecta.domain.Consultor;
import br.com.projeto.conecta.domain.Usuarios;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Integer> {

	@Query("select a from Agendamento a join fetch a.pedido where a.disponivel.consultor.email = :email")
	List<Agendamento> findByConsultor(@Param("email")String email);

	@Query("select u from Agendamento u where u.criadoPor = :usuario")
	List<Agendamento> findByCliente(@Param("usuario")Usuarios usuario);
	
	@Query("select u from Agendamento u where u.pedido.status = 'aguardando'")
	List<Agendamento> findByStatus();
	
}