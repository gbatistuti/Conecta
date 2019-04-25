package br.com.projeto.conecta.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="recursos")
public class Consultor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Integer codConsultor;
	private String nome;
	private String email;
	private String senha;
	private String unidade;
	private String cargo;
	private Float preco;
	
	public Consultor(Integer id, Integer codConsultor, String nome, String email, String senha, String unidade,
			String cargo, Float preco) {
		this.id = id;
		this.codConsultor = codConsultor;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.unidade = unidade;
		this.cargo = cargo;
		this.preco = preco;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCodConsultor() {
		return codConsultor;
	}
	public void setCodConsultor(Integer codConsultor) {
		this.codConsultor = codConsultor;
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
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public Float getPreco() {
		return preco;
	}
	public void setPreco(Float preco) {
		this.preco = preco;
	}
}
