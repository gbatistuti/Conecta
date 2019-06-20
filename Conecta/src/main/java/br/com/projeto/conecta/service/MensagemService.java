package br.com.projeto.conecta.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import br.com.projeto.conecta.domain.Disponiveis;
import br.com.projeto.conecta.domain.Mensagem;
import br.com.projeto.conecta.domain.Pedido;

@Service
public class MensagemService {
	
	@Autowired
	private PedidoService pedidoService;
	
	private Queue<Mensagem> filaMensagemService = new LinkedList<Mensagem>();
	
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	String hoje = LocalDate.now().format(formatter);
	
	public void emailApontamento(Disponiveis disponivel) {
		Mensagem mensagem = new Mensagem(disponivel.getConsultor().getEmail(),
				"Apontamento realizado | " + hoje,
				"Olá, " + disponivel.getConsultor().getNome() 
						+ "\nID do apontamento: "	+ disponivel.getIdDisponivel() 
						+ "\nInício às: " + disponivel.getHoraInicio()
						+ "\nFim às: " + disponivel.getHoraFim() 
						+ "\nRealizado com sucesso!"
						+ "\n\nAtenciosamente,"
						+ "\nEquipe TOTVS Conecta");
		
		filaMensagemService.add(mensagem);
	}
	
	
	@Async
	public void emailCandidatura(Disponiveis disponivel, Integer idPedido) {
		Pedido pedido = pedidoService.getInformacoesEmailCandidatura(idPedido);
		Mensagem mensagem = new Mensagem(disponivel.getConsultor().getEmail(),
				"Candidatura realizada | " + hoje,
				"Olá, " + disponivel.getConsultor().getNome() 
						+ "\nID do pedido: "	+ pedido.getIdPedido()
						+ "\nCliente: " + pedido.getProjeto().getCliente().getNome() 
						+ "\nProjeto: " + pedido.getProjeto().getNome()
						+ "\nTítulo do Pedido: " + pedido.getTitulo()
						+ "\nDescrição do Pedido: " + pedido.getDescricao()
						+ "\nHoras contratadas: " + pedido.getSugestaoDeHoras()
						+ "\n\nAtenciosamente,"
						+ "\n\nEquipe TOTVS Conecta");
		
		filaMensagemService.add(mensagem);
	}
	
	
	public Mensagem getMensagem() {
		if(filaMensagemService.isEmpty()) {
			return null;
		}
		return filaMensagemService.poll();
	}

}
