package br.com.projeto.conecta.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.projeto.conecta.domain.Disponivel;

@Repository
public interface DisponivelRepository extends JpaRepository<Disponivel, Integer>{

	@Query("select u from Disponiveis u where u.data = :hoje")
	List<Disponivel> findByDate(@Param("hoje") Date data);
	

	@Query("select u from Disponiveis u join fetch u.consultor where u.data = :data and u.consultor.email = :email")
	Disponivel findByUserAndDate(@Param("data")Date data, @Param("email") String email);
}