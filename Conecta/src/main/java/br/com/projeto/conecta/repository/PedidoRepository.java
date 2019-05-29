package br.com.projeto.conecta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.projeto.conecta.domain.Pedido;
import br.com.projeto.conecta.domain.Usuarios;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer>{

	@Query("select u from Pedido u where u.criadoPor = :usuario and u.origem = 'pedido'")
	List<Pedido> findByCliente(Usuarios usuario);
	
	@Query("select u from Pedido u where u.candidatura=false and u.origem='pedido'")
	List<Pedido> getPedidoFiltrado();
	
}
