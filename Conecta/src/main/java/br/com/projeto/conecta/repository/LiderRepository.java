package br.com.projeto.conecta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.projeto.conecta.domain.Lider;

@Repository
public interface LiderRepository extends JpaRepository<Lider, Integer>{
	
	@Query("select u from Lider u where u.idUsuario = :currentUserId")
	Lider getById(Integer currentUserId);

}
