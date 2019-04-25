package br.com.projeto.conecta.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "lideres")
public class Lider {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer codLider;
	private String nome;
	private String email;
	private String senha;
	private String unidade;
	
	public Lider(Integer id, Integer codLider, String nome, String email, String senha, String unidade) {
		this.id = id;
		this.codLider = codLider;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.unidade = unidade;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCodLider() {
		return codLider;
	}

	public void setCodLider(Integer codLider) {
		this.codLider = codLider;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	
}