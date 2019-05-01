package br.com.projeto.conecta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.projeto.conecta.domain.Cliente;
import br.com.projeto.conecta.domain.Credenciais;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
	
	@Query("from Cliente")
	public boolean contemClienteComEssas(Credenciais credenciais);
}