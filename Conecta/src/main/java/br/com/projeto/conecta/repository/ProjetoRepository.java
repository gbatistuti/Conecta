package br.com.projeto.conecta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.projeto.conecta.domain.Projeto;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Integer> {

	@Query("select u from Projeto u where u.cliente.email = :email")
	List<Projeto> getByEmail(@Param("email") String email);

}
