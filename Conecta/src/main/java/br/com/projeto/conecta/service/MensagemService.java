package br.com.projeto.conecta.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import br.com.projeto.conecta.domain.Agendamento;
import br.com.projeto.conecta.domain.Disponiveis;
import br.com.projeto.conecta.domain.Mensagem;
import br.com.projeto.conecta.domain.Pedido;

@Service
@Async
public class MensagemService {

	@Autowired
	private MensageiroService mensageiroService;
	@Autowired
	private PedidoService pedidoService;

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	String hoje = LocalDate.now().format(formatter);

	@Async
	public void enviarMensagemApontamento(Disponiveis disponivel) {
		Mensagem mensagem = new Mensagem(disponivel.getConsultor().getEmail(),
				"Apontamento realizado | " + hoje,
				"Olá, " + disponivel.getConsultor().getNome() + " \n\n\n"
						+ "ID do apontamento: "	+ disponivel.getIdDisponivel() 
						+ "\nInício às: " + disponivel.getHoraInicio()
						+ "\nFim às: " + disponivel.getHoraFim() 
						+ "\n\nRealizado com sucesso!"
						+ "\n\n\n\nAtenciosamente,"
						+ "\n\nEquipe TOTVS Conecta");
		
		mensageiroService.enviar(mensagem);
	}
	
	@Async
	public void enviarMensagemCandidatura(Disponiveis disponivel, Agendamento agendamento) {
		
		Mensagem mensagem = new Mensagem(disponivel.getConsultor().getEmail(),
				"Candidatura realizada | " + hoje,
				"Olá, " + disponivel.getConsultor().getNome() + "\n\n\n"
						+ "ID do pedido: "	+ agendamento.getPedido().getIdPedido()
						+ "\nCliente: " + agendamento.getPedido().getProjeto().getCliente() 
						+ "\nProjeto: " + agendamento.getPedido().getProjeto()
						+ "\n Título do Pedido: " + agendamento.getPedido().getTitulo()
						+ "\n Descrição do Pedido: " + agendamento.getPedido().getDescricao()
						+ "\n Horas contratadas: " + agendamento.getPedido().getSugestaoDeHoras()
						+ "\n\n\n\nAtenciosamente,"
						+ "\n\nEquipe TOTVS Conecta");
		
		mensageiroService.enviar(mensagem);
		System.out.println("---------------ENVIADO CANDIDATURA------------");
	}

}
