package br.com.projeto.conecta.security;

import java.util.Collection;

import javax.transaction.Transactional;

import org.hibernate.validator.internal.util.privilegedactions.GetMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import br.com.projeto.conecta.domain.Consultor;
import br.com.projeto.conecta.domain.Usuarios;
import br.com.projeto.conecta.repository.ConsultorRepository;
import br.com.projeto.conecta.repository.GrupoRepository;
import br.com.projeto.conecta.repository.UsuariosRepository;

@Repository
@Transactional
public class ConectaUserDetailsService implements UserDetailsService {

	@Autowired
	private UsuariosRepository usuariosRepository;

	@Autowired
	private GrupoRepository grupoRepository;
	
	@Autowired
	private ConsultorRepository consultorRepository;
	

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Usuarios usuario = usuariosRepository.findByEmail(userName);

		if (usuario == null) {
			throw new UsernameNotFoundException("Usuário não encontrado!!");
		}

		return new User(usuario.getUsername(), usuario.getPassword(), usuario.getAuthorities());
	}

	public Collection<? extends GrantedAuthority> authorities(Usuarios usuario) {
		return authorities(grupoRepository.findByUsuariosIn(usuario));
	}

	public Integer getCurrentUserId() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) auth.getPrincipal();
		String userName =  user.getUsername();
		Integer usuarioLogado = usuariosRepository.findByEmail(userName).getIdUsuario();
		return usuarioLogado;
	}
	
	public Usuarios getCurrentUser() {
		return usuariosRepository.getById(getCurrentUserId()) ;
	}
	
	public Consultor getCurrentConsultor() {
		return consultorRepository.getById(getCurrentUserId()) ;
	}
	

}
