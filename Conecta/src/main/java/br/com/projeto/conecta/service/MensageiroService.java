package br.com.projeto.conecta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import br.com.projeto.conecta.domain.Mensagem;

@Component
public class MensageiroService {
	
	@Autowired
	private JavaMailSender javaMailSender;

	public void enviar(Mensagem mensagem) {

		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

		simpleMailMessage.setFrom(mensagem.getRemetente());
		simpleMailMessage.setTo(mensagem.getDestinatarios());
		simpleMailMessage.setSubject(mensagem.getAssunto());
		simpleMailMessage.setText(mensagem.getCorpo());

		javaMailSender.send(simpleMailMessage);

	}

}
