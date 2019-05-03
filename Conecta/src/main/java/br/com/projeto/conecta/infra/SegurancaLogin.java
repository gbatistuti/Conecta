package br.com.projeto.conecta.infra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.projeto.conecta.domain.Cliente;
import br.com.projeto.conecta.domain.Lider;
import br.com.projeto.conecta.repository.LiderRepository;
import br.com.projeto.conecta.repository.UsuariosRepository;

@Component
public class SegurancaLogin {
	private final UsuariosRepository usuariosRepository;
	
	@Autowired
	public SegurancaLogin(UsuariosRepository usuariosRepository){
		this.usuariosRepository = usuariosRepository;
	}
	
	public Integer permitirAcesso(String email, String senha) {
		
		Integer idUsuario = usuariosRepository.contemUsuario(email, senha);
		
		if(idUsuario != null) {
			return idUsuario;
		} else {
			return -1;
		}
	}
	
	public String validarGrupo(Integer idUsuario) {
		String grupo = usuariosRepository.buscaGrupo(idUsuario);
		
		return grupo;
	}
}
