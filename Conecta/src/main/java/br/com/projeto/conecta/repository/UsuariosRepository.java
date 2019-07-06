package br.com.projeto.conecta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.projeto.conecta.domain.Usuario;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuario, Integer>{

	@Query("select u from Usuarios u join fetch u.grupo where u.email = :userName")
	Usuario findByEmail(String userName);

	@Query("select u from Usuarios u where u.idUsuario = :currentUserId")
	Usuario getById(Integer currentUserId);
	
}
