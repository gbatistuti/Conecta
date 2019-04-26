package br.com.projeto.conecta.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.projeto.conecta.domain.Consultor;
import br.com.projeto.conecta.domain.Credenciais;

@Repository
public interface ConsultorRepository extends JpaRepository<Consultor, Integer>{
	
	@Query("from Consultor")
	public boolean contemConsultorComEssas(Credenciais credenciais);
}
