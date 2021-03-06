package br.com.projeto.conecta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.projeto.conecta.domain.Projeto;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Integer> {

	@Query("select u from Projeto u join fetch u.cliente")
	List<Projeto> findAll();
	
	@Query("select u from Projeto u where u.cliente.email = :email")
	List<Projeto> getByEmail(@Param("email") String email);

	@Transactional
	@Modifying
	@Query("update Projeto set qtdCreditos = qtdCreditos - :creditosParaDescontar where idProjeto = :idProjeto")
	void debitarCreditos(@Param("creditosParaDescontar") float creditosParaDescontar, @Param("idProjeto") Integer idProjeto);
	
	@Transactional
	@Modifying
	@Query("update Projeto set qtdCreditos = :qtdCreditos where idProjeto = :idProjeto")	
	void atualizarCreditos(Float qtdCreditos, Integer idProjeto);

}
