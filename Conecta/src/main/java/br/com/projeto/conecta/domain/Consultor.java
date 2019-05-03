package br.com.projeto.conecta.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="RECURSOS")
@PrimaryKeyJoinColumn(name="idUsuario")
public class Consultor extends Usuarios{
	
	private String unidade;
	private String cargo;
	private Float preco;
	
	
	
	public Consultor(String email, String senha, Integer idUsuario, String codigo, String nome, String grupo,
			String unidade, String cargo, Float preco) {
		super(email, senha, idUsuario, codigo, nome, grupo);
		this.unidade = unidade;
		this.cargo = cargo;
		this.preco = preco;
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
