package br.com.projeto.conecta.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "LIDERES")
@PrimaryKeyJoinColumn(name = "idUsuario")
public class Lider extends Usuarios {

	private String unidade;

	@OneToMany(mappedBy = "CriadoPor")
	private List<Alocacoes> alocacao;

	public Lider() {
	}

	public List<Alocacoes> getAlocacao() {
		return alocacao;
	}

	public void setAlocacao(List<Alocacoes> alocacao) {
		this.alocacao = alocacao;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}
}
