package br.com.projeto.conecta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.projeto.conecta.domain.Agendamento;
import br.com.projeto.conecta.domain.Consultor;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Integer> {

	@Query("select u from Agendamento u where u.criadoPor = :consultor")
	List<Agendamento> findByConsultor(@Param("consultor")Consultor consultor);
	
	@Query("update Agendamento u set u.pedido.status where u.idAgendamento = :idAgendamento")
	void alterarStatusParaAprovado(@Param("idAgendamento") int IdAgendamento);
}