package br.com.projeto.conecta.security;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import br.com.projeto.conecta.domain.Consultor;
import br.com.projeto.conecta.domain.Lider;
import br.com.projeto.conecta.domain.Usuarios;
import br.com.projeto.conecta.repository.ConsultorRepository;
import br.com.projeto.conecta.repository.LiderRepository;
import br.com.projeto.conecta.repository.UsuariosRepository;

@Repository
@Transactional
public class ConectaUserDetailsService implements UserDetailsService {

	@Autowired
	private UsuariosRepository usuariosRepository;

	@Autowired
	private ConsultorRepository consultorRepository;
	
	@Autowired
	LiderRepository liderRepository;
	

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Usuarios usuario = usuariosRepository.findByEmail(userName);

		if (usuario == null) {
			throw new UsernameNotFoundException("Usuário não encontrado!!");
		}

		return new User(usuario.getUsername(), usuario.getPassword(), usuario.getAuthorities());
	}
	
	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	public String getCurrentUserEmail() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}

	public Integer getCurrentUserId() {
		Integer usuarioLogado = usuariosRepository.findByEmail(getCurrentUserEmail()).getIdUsuario();
		return usuarioLogado;
	}
	
	public Usuarios getCurrentUser() {
		String email = getCurrentUserEmail();
		return usuariosRepository.findByEmail(email);
	}
	
	public Consultor getCurrentConsultor() {
		String email = getCurrentUserEmail();
		return consultorRepository.findByEmail(email);
	}
	
	public Lider getCurrentLider() {
		String email = getCurrentUserEmail();
		return liderRepository.findByEmail(email);
	}

}

