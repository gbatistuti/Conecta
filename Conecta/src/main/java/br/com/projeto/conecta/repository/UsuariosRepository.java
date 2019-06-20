package br.com.projeto.conecta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.projeto.conecta.domain.Usuarios;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuarios, Integer>{

//	@Query("select u.idUsuario, u.email, u.senha, u.idUsuario.grupo from Usuarios u join fetch u.idUsuario.grupo where u.email = :userName")
	@Query("select u from Usuarios u join fetch u.grupo where u.email = :userName")
	Usuarios findByEmail(String userName);

	@Query("select u from Usuarios u where u.idUsuario = :currentUserId")
	Usuarios getById(Integer currentUserId);
	
}
