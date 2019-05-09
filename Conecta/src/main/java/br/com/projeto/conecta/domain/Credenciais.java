package br.com.projeto.conecta.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Credenciais {
	private String email;
	private String senha;
	
	public Credenciais() {
	}
	public Credenciais(String email, String senha) {
		this.email = email;
		this.senha = senha;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
}
