package br.com.projeto.conecta.controller

import static org.mockito.Mockito.when

import javax.servlet.http.HttpServletRequest

import org.mockito.Mockito
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.HttpRequest

import spock.lang.Specification

class LoginControllerTeste extends Specification {
	def 'Teste LoginController passando cliente'(){
		
		given: 'Mocka'
		
		def loginControle = new LoginController()
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class)
		Mockito.when(request.isUserInRole("ROLE_CLIENTE")).thenReturn(true)
		
		when: 'DA'
		def retorno = loginControle.index(request)
		
		then: "RETORNA"
		retorno == "redirect:/homeCliente"
		
	}
	
	def 'Teste LoginController passando consultor'(){
		
		given: 'Mocka'
		
		def loginControle = new LoginController()
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class)
		Mockito.when(request.isUserInRole("ROLE_CONSULTOR")).thenReturn(true)
		
		when: 'DA'
		def retorno = loginControle.index(request)
		
		then: "RETORNA"
		retorno == "redirect:/homeConsultor"
		
	}
	
	def 'Teste LoginController passando lider'(){
		
		given: 'Mocka'
		
		def loginControle = new LoginController()
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class)
		Mockito.when(request.isUserInRole("ROLE_LIDER")).thenReturn(true)
		
		when: 'DA'
		def retorno = loginControle.index(request)
		
		then: "RETORNA"
		retorno == "redirect:/homeLider"
		
	}
	
	def 'Teste LoginController passando lider esperando erro'(){
		
		given: 'Mocka'
		
		def loginControle = new LoginController()
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class)
		Mockito.when(request.isUserInRole("ROLE_ERRO")).thenReturn(true)
		
		when: 'DA'
		def retorno = loginControle.index(request)
		
		then: "RETORNA"
		retorno == "login?error"
		
	}
	
	
}
