package br.com.projeto.conecta.domain;

public abstract class Usuarios extends Credenciais {
	
	private Integer codigo;
	private String nome;
	
	public Usuarios(String email, String senha, Integer codigo, String nome) {
		super(email, senha);
		this.codigo = codigo;
		this.nome = nome;
	}
	
}
