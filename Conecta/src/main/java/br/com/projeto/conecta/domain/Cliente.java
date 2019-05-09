package br.com.projeto.conecta.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="CLIENTES")
@PrimaryKeyJoinColumn(name="idUsuario")
public class Cliente extends Usuarios{

	private String telefone;
	
	@OneToMany(mappedBy = "cliente")
	private List<Projeto> projeto;
	
	public Cliente() {
	}

	public Cliente(Integer idUsuario, String codigo, String nome, String grupo, Credenciais credenciais, String telefone) {
		super(idUsuario, codigo, nome, grupo, credenciais);
		this.telefone = telefone;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public List<Projeto> getProjeto() {
		return projeto;
	}

	public void setProjeto(List<Projeto> projeto) {
		this.projeto = projeto;
	}
}
