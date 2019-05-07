package br.com.projeto.conecta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.projeto.conecta.domain.Projeto;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Integer> {

//	@Query(value = "from projetos where id_usuario = ?1" )
//	List<Projeto> buscarByIdLider();

}
