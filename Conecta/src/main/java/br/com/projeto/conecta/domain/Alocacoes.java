package br.com.projeto.conecta.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ALOCACAO")
public class Alocacoes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idAlocacao;

	private Integer horas;

	@OneToOne
	@JoinColumn(name = "ID_DISPONIVEL")
	private Disponiveis disponiveis;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_LIDER")
	private Lider lider;

	public Integer getIdAlocacao() {
		return idAlocacao;
	}

	public void setIdAlocacao(Integer idAlocacao) {
		this.idAlocacao = idAlocacao;
	}

	public Integer getHoras() {
		return horas;
	}

	public void setHoras(Integer horas) {
		this.horas = horas;
	}

	public Disponiveis getDisponiveis() {
		return disponiveis;
	}

	public void setDisponiveis(Disponiveis disponiveis) {
		this.disponiveis = disponiveis;
	}

	public Lider getLider() {
		return lider;
	}

	public void setLider(Lider lider) {
		this.lider = lider;
	}

}