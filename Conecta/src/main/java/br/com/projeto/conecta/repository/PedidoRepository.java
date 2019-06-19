package br.com.projeto.conecta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.projeto.conecta.domain.Pedido;
import br.com.projeto.conecta.domain.Usuarios;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer>{

	@Query("select u from Pedido u where u.criadoPor = :usuario and u.origem = 'pedido'")
	List<Pedido> findByCliente(Usuarios usuario);
	
	@Query("select u from Pedido u join fetch u.projeto where u.candidatura=false and u.origem='pedido' and status='aguardando'")
	List<Pedido> getPedidoFiltrado();
	
	@Query("select u from Pedido u where u.status = 'aguardando'")
	List<Pedido> findByStatus();

	@Transactional
	@Modifying
	@Query("update Pedido set candidatura = true where idPedido = :idPedido")
	void atualizaCandidatura(@Param("idPedido") Integer idPedido);
	
	@Transactional
	@Modifying
	@Query("update Pedido set status = :status where idPedido = :idPedido")
	void atualizaStatus(@Param("status")String status, @Param("idPedido")Integer idPedido);
}