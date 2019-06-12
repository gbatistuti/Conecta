package br.com.projeto.conecta.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.projeto.conecta.domain.Disponiveis;

@Repository
public interface DisponivelRepository extends JpaRepository<Disponiveis, Integer>{

	@Query("select u from Disponiveis u where u.data = :hoje")
	List<Disponiveis> findByDate(@Param("hoje") Date data);
	

	@Query("select u from Disponiveis u join fetch u.consultor where u.data = :data and u.consultor.email = :email")
	Disponiveis findByUserAndDate(@Param("data")Date data, @Param("email") String email);
	
//	@Query("select u.horaFim from Disponiveis u where u.idDisponivel = :disponiveis and u.data")
//	LocalTime findByUltimaHoraDaDisponibilidade(Date data, Integer disponiveis);
}