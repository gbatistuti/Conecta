package br.com.projeto.conecta.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "LIDERES")
@PrimaryKeyJoinColumn(name = "idUsuario")
public class Lider extends Usuario {

	@Column(nullable = false)
	private String unidade;

	@OneToMany(mappedBy = "lider")
	private List<Alocacao> alocacao;

	public Lider() {
	}

	public List<Alocacao> getAlocacao() {
		return alocacao;
	}

	public void setAlocacao(List<Alocacao> alocacao) {
		this.alocacao = alocacao;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}
}
