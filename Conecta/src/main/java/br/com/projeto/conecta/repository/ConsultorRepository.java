package br.com.projeto.conecta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.projeto.conecta.domain.Consultor;

@Repository
public interface ConsultorRepository extends JpaRepository<Consultor, Integer>{
	
	@Query("select u from Consultor u where u.idUsuario = :currentUserId")
	Consultor getById(Integer currentUserId);

	Consultor findByEmail(String email);
}
