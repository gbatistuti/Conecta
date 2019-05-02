package br.com.projeto.conecta.infra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.projeto.conecta.domain.Lider;
import br.com.projeto.conecta.repository.LiderRepository;

@Component
public class SegurancaLogin {
	private final LiderRepository liderRepository;
	
	@Autowired
	public SegurancaLogin(LiderRepository liderRepository){
		this.liderRepository = liderRepository;
	}
	
	public boolean permitirAcesso(String email, String senha) {
		
		if(liderRepository.contemLider(email, senha) != null) {
			return true;
		} else {
			return false;
		}
	}
}
