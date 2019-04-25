package br.com.projeto.conecta.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "lideres")
public class Lider extends Usuarios{
	
	private String unidade;

	public Lider(String email, String senha, Integer id, Integer codigo, String nome, String unidade) {
		super(email, senha, id, codigo, nome);
		this.unidade = unidade;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}
	
}