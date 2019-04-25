package br.com.projeto.conecta.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="clientes")
public class Cliente extends Usuarios{

	private String telefone;

	public Cliente(String email, String senha, Integer id, Integer codigo, String nome, String telefone) {
		super(email, senha, id, codigo, nome);
		this.telefone = telefone;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

}
