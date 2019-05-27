package br.com.projeto.conecta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projeto.conecta.domain.Pedido;
import br.com.projeto.conecta.domain.Usuarios;
import br.com.projeto.conecta.repository.PedidoRepository;
import br.com.projeto.conecta.security.ConectaUserDetailsService;

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

	public List<Pedido> buscarPedidosPorUsuario(Usuarios usuario) {
		return pedidoRepository.findByCliente(usuario);
	}

}