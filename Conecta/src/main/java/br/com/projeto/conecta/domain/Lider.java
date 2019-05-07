package br.com.projeto.conecta.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "LIDERES")
@PrimaryKeyJoinColumn(name="idUsuario")
public class Lider extends Usuarios{
	
	private String unidade;


	public Lider(String email, String senha, Integer idUsuario, String codigo, String nome, String grupo,
			String unidade) {
		super(email, senha, idUsuario, codigo, nome, grupo);
		this.unidade = unidade;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}
}