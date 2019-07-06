package br.com.projeto.conecta.security;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import br.com.projeto.conecta.domain.Consultor;
import br.com.projeto.conecta.domain.Lider;
import br.com.projeto.conecta.domain.Usuario;
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
		Usuario usuario = usuariosRepository.findByEmail(userName);

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

	@Cacheable(value = "idUser")
	public Integer getCurrentUserId() {
		Integer usuarioLogado = usuariosRepository.findByEmail(getCurrentUserEmail()).getIdUsuario();
		return usuarioLogado;
	}
	
	@Cacheable(value = "user")
	public Usuario getCurrentUser() {
		String email = getCurrentUserEmail();
		return usuariosRepository.findByEmail(email);
	}
	
	@Cacheable(value = "consultor")
	public Consultor getCurrentConsultor() {
		String email = getCurrentUserEmail();
		return consultorRepository.findByEmail(email);
	}
	
	@Cacheable(value = "lider")
	public Lider getCurrentLider() {
		String email = getCurrentUserEmail();
		return liderRepository.findByEmail(email);
	}

}

