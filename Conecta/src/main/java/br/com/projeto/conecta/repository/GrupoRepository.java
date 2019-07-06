package br.com.projeto.conecta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projeto.conecta.domain.Grupo;
import br.com.projeto.conecta.domain.Usuario;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Integer> {

	Usuario findByUsuariosIn(Usuario usuario);
	
}
