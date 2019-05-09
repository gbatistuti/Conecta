package br.com.projeto.conecta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.projeto.conecta.domain.Lider;

@Repository
public interface LiderRepository extends JpaRepository<Lider, Integer>{
	
//	@Query("from Lider")
//	public boolean contemLider(String email, String senha);
	
	@Query(value="select l.id from Lider l where l.credenciais.email = :email and l.credenciais.senha = :senha")
	Integer contemLider(@Param("email") String email, @Param("senha") String senha);
	
}
