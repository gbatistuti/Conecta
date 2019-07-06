package br.com.projeto.conecta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.projeto.conecta.domain.HabilidadeEspecifica;

@Repository
public interface HabilidadeEspecificaRepository extends JpaRepository<HabilidadeEspecifica, Integer>{

	@Query(value="select u.nomeHabilidadeEspecifica from HabilidadeEspecifica u where u.consultor = :idDisponivel")
	String buscaHabilidadesEspecificasPorIdDisponivel(@Param("idDisponivel") Integer idDisponivel);
	
}