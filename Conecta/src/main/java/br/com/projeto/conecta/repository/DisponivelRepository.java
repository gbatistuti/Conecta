package br.com.projeto.conecta.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.projeto.conecta.domain.Disponivel;

@Repository
public interface DisponivelRepository extends JpaRepository<Disponivel, Integer>{

	@Query("select u from Disponivel u where u.logData = :hoje")
	List<Disponivel> findByDate(@Param("hoje") LocalDate data);
	

	@Query("select u from Disponivel u join fetch u.consultor where u.logData = :data and u.consultor.email = :email")
	Disponivel findByUserAndDate(@Param("data")LocalDate data, @Param("email") String email);
}