package br.com.projeto.conecta.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import br.com.projeto.conecta.domain.Disponiveis;
import br.com.projeto.conecta.domain.Mensagem;

@Service
@Async
public class MensagemService {
	
	private Mensagem mensagem = null;
	
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	String hoje = LocalDate.now().format(formatter);
	
	public void enviarMensagemApontamento(Disponiveis disponivel) {
		Mensagem mensagem = new Mensagem(disponivel.getConsultor().getEmail(),
				"Apontamento realizado | " + hoje,
				"Olá, " + disponivel.getConsultor().getNome() 
						+ "\nID do apontamento: "	+ disponivel.getIdDisponivel() 
						+ "\nInício às: " + disponivel.getHoraInicio()
						+ "\nFim às: " + disponivel.getHoraFim() 
						+ "\nRealizado com sucesso!"
						+ "\nAtenciosamente,"
						+ "\nEquipe TOTVS Conecta");
		
		setMensagem(mensagem);
	}
	
	public Mensagem getMensagem() {
		if(mensagem != null) {
			Mensagem envia = mensagem;
			mensagem = null;
			return envia;
		}
		return mensagem;
	}

	public void setMensagem(Mensagem mensagem) {
		this.mensagem = mensagem;
	}
	
	

}
