package br.com.projeto.conecta.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public abstract class Usuarios extends Credenciais {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Integer codigo;
	private String nome;
	public Usuarios(String email, String senha, Integer id, Integer codigo, String nome) {
		super(email, senha);
		this.id = id;
		this.codigo = codigo;
		this.nome = nome;
	}
}
