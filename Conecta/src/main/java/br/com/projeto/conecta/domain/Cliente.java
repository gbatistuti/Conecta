package br.com.projeto.conecta.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="CLIENTES")
@PrimaryKeyJoinColumn(name="idUsuario")
public class Cliente extends Usuarios{
	
	private String telefone;

	public Cliente(String email, String senha, Integer idUsuario, String codigo, String nome, String grupo,
			String telefone) {
		super(email, senha, idUsuario, codigo, nome, grupo);
		this.telefone = telefone;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
}
