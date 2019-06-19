package br.com.projeto.conecta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.conecta.domain.Mensagem;
import br.com.projeto.conecta.service.MensagemService;

@RestController
@RequestMapping(value = "/mensagem")
public class MensagemController {
	
	@Autowired
	private MensagemService mensagemService;
	
	@GetMapping
	public ResponseEntity<Mensagem> enviarMensagemApontamento() {
		
		Mensagem mensagem = mensagemService.getMensagem();
		if(mensagem != null) {
			return ResponseEntity.ok().body(mensagem);
		}
		return null;
	}
	
	
}
