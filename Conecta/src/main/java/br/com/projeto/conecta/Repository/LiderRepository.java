package br.com.projeto.conecta.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.projeto.conecta.domain.Credenciais;
import br.com.projeto.conecta.domain.Lider;

@Repository
public interface LiderRepository extends JpaRepository<Lider, Integer>{
	
	@Query("from Lider")
	public boolean contemLiderComEssas(Credenciais credenciais);
}
