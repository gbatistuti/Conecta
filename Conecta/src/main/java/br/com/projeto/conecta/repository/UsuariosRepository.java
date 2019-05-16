package br.com.projeto.conecta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.projeto.conecta.domain.Usuarios;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuarios, Integer>{


	Usuarios findByEmail(String userName);

	@Query("select u from Usuarios u where u.idUsuario = :currentUserId")
	Usuarios getById(Integer currentUserId);
	
}
