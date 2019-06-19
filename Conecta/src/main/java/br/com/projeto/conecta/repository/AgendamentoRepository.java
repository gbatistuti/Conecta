package br.com.projeto.conecta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.projeto.conecta.domain.Agendamento;
import br.com.projeto.conecta.domain.Consultor;
import br.com.projeto.conecta.domain.Usuarios;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Integer> {

	@Query("select a from Agendamento a join fetch a.pedido where a.disponivel.consultor.email = :email")
	List<Agendamento> findByConsultor(@Param("email")String email);

	@Query("select u from Agendamento u join fetch u.pedido where u.criadoPor = :usuario")
	List<Agendamento> findByCliente(@Param("usuario")Usuarios usuario);
	
	@Query("select u from Agendamento u join fetch u.pedido p join fetch p.projeto join fetch u.disponivel d join fetch d.consultor where u.pedido.status = 'aguardando'")
	List<Agendamento> findByStatus();

	@Query("select a.disponivel.consultor.creditosPorHora from Agendamento a where a.idAgendamento = :idAgendamento")
	float findCreditosPorHora(@Param("idAgendamento")Integer idAgendamento);

	@Query("select a.pedido.sugestaoDeHoras from Agendamento a where a.idAgendamento = :idAgendamento")
	int findHoras(@Param("idAgendamento")Integer idAgendamento);

	@Query("select (a.pedido.sugestaoDeHoras * a.disponivel.consultor.creditosPorHora) from Agendamento a where a.idAgendamento = :idAgendamento")
	float calcularCreditosPorHoraVezesSugestaoDeHoras(@Param("idAgendamento")Integer idAgendamento);

	@Query("select a.pedido.projeto.idProjeto from Agendamento a where a.idAgendamento = :idAgendamento")
	Integer findIdProjeto(@Param("idAgendamento")Integer idAgendamento);
	
}