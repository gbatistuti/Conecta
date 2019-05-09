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
	
	public Lider() {
	}

	public Lider(Integer idUsuario, String codigo, String nome, String grupo, Credenciais credenciais, String unidade) {
		super(idUsuario, codigo, nome, grupo, credenciais);
		this.unidade = unidade;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}
}