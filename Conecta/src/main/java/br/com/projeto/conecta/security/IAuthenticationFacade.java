package br.com.projeto.conecta.security;

import org.springframework.security.core.Authentication;

public interface IAuthenticationFacade {
	Authentication getAuthentication();
}


