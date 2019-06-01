package br.com.projeto.conecta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	public void salvarPedido(Pedido pedido) {
		pedidoRepository.save(pedido);
	}
	
	public List<Pedido> filtrarPorOrigemECandidatura () {
		return pedidoRepository.getPedidoFiltrado();
	}
	
	public Pedido getPedido(Integer id) {
		return pedidoRepository.getOne(id);
	}
	
	public List<Pedido> buscarPedidosPorUsuario(Usuarios usuario) {
		return pedidoRepository.findByCliente(usuario);
	}
	
	public List<Pedido>buscarPorStatus(){
		return pedidoRepository.findByStatus();
	}

}