package br.com.projeto.conecta.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "usuarios")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Usuarios extends Credenciais implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idUsuario;
	
	private Integer codigo;
	private String nome;
	private String tipo;
	
	public Usuarios(String email, String senha, Integer codigo, String nome) {
		super(email, senha);
		this.codigo = codigo;
		this.nome = nome;
	}
	
}
