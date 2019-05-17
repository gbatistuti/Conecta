package br.com.projeto.conecta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projeto.conecta.domain.Grupo;
import br.com.projeto.conecta.domain.Usuarios;

public interface GrupoRepository extends JpaRepository<Grupo, Integer> {

	Usuarios findByUsuariosIn(Usuarios usuario);
	
}
