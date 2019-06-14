package br.com.projeto.conecta.component;

import java.time.LocalDate;

import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import br.com.projeto.conecta.domain.Disponiveis;
import br.com.projeto.conecta.domain.Mensagem;

@Component
public class MensagemComponent {
	
	private final RestTemplate restTemplate;
	private final String url;
	
	public MensagemComponent() {
		this.restTemplate = new RestTemplate();
		this.url = "http://localhost:8080/homeConsultor/";
	}
	
	
	public void enviarMensagemApontamento(Disponiveis disponivel) {
		Mensagem mensagem = new Mensagem("conecta-mensagem@gmail.com", "gustavo.batistuti1@gmail.com",
				"Apontamento realizado | " + LocalDate.now(),
				"Olá, " + disponivel.getConsultor().getNome() + " seu apontamento de id: "
						+ disponivel.getIdDisponivel() + " com Início às: " + disponivel.getHoraInicio()
						+ " e Fim às: " + disponivel.getHoraFim() + " foi realizado com sucesso!");
		
		ResponseEntity<String> resposta = this.restTemplate.postForEntity(url, mensagem, String.class);
		
		System.out.println(resposta);
	}
	
	

}
