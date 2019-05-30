package br.com.projeto.conecta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projeto.conecta.domain.Alocacoes;

public interface AlocacaoRepository extends JpaRepository<Alocacoes, Integer> {
	

}
