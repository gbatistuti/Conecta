package br.com.projeto.conecta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.projeto.conecta.domain.Usuarios;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuarios, Integer>{
	@Query(value="select u.id from Usuarios u where u.credenciais.email = :email and u.credenciais.senha = :senha")
	Integer contemUsuario(@Param("email") String email, @Param("senha") String senha);
	
	@Query(value="select u.grupo from Usuarios u where u.id = :idUsuario")
	String buscaGrupo(@Param("idUsuario") Integer idUsuario);
	
}
