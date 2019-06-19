package br.com.projeto.conecta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import br.com.projeto.conecta.domain.Agendamento;
import br.com.projeto.conecta.domain.Pedido;
import br.com.projeto.conecta.domain.Usuarios;
import br.com.projeto.conecta.repository.PedidoRepository;

@Service
public class PedidoService {
	
	@Autowired
	PedidoRepository pedidoRepository;

	public List<Pedido> buscarTodos(){
		return pedidoRepository.findAll();
	}
	
	@CacheEvict(value = {"pedidosPorUsuarioCache", "pedidosFiltradosPorOrigemECandidaturaCache"}, allEntries = true)
	public void salvarPedido(Pedido pedido) {
		pedidoRepository.save(pedido);
	}
	
	@Cacheable(value = "pedidosFiltradosPorOrigemECandidaturaCache")
	public List<Pedido> filtrarPorOrigemECandidatura () {
		return pedidoRepository.getPedidoFiltrado();
	}
	
	public Pedido getPedido(Integer id) {
		return pedidoRepository.getOne(id);
	}
	
	@Cacheable(value = "pedidosPorUsuarioCache")
	public List<Pedido> buscarPedidosPorUsuario(Usuarios usuario) {
		return pedidoRepository.findByCliente(usuario);
	}
	
	public List<Pedido>buscarPorStatus(){
		return pedidoRepository.findByStatus();
	}
	
	public void atualizarCandidaturaPedido(Integer idPedido) {
		pedidoRepository.atualizaCandidatura(idPedido);
	}
	
	public void atualizarStatus(String status, Agendamento agendamento) {
		pedidoRepository.atualizaStatus(status, agendamento.getPedido().getIdPedido());
	}

}