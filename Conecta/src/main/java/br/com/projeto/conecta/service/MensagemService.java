package br.com.projeto.conecta.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import br.com.projeto.conecta.domain.Disponiveis;
import br.com.projeto.conecta.domain.Mensagem;

@Component
@Async
public class MensagemService {
	
	@Autowired
	private MensageiroService mensageiroService;
	
	@Async
	public void enviarMensagemApontamento(Disponiveis disponivel) {
		Mensagem mensagem = new Mensagem("conecta-mensagem@gmail.com", "gustavo.batistuti1@gmail.com",
				"Apontamento realizado | " + LocalDate.now(),
				"Olá, " + disponivel.getConsultor().getNome() + " seu apontamento de id: "
						+ disponivel.getIdDisponivel() + " com Início às: " + disponivel.getHoraInicio()
						+ " e Fim às: " + disponivel.getHoraFim() + " foi realizado com sucesso!");
		
		mensageiroService.enviar(mensagem);
		
	}
	
	

}
