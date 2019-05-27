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
	
	private String motivo;

	@OneToOne
	@JoinColumn(name = "ID_AGENDAMENTO")
	private Agendamento agendamento;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_LIDER")
	private Lider CriadoPor;
	
	public Alocacoes() {}

	public Integer getIdAlocacao() {
		return idAlocacao;
	}

	public void setIdAlocacao(Integer idAlocacao) {
		this.idAlocacao = idAlocacao;
	}

	public Lider getCriadoPor() {
		return CriadoPor;
	}

	public void setCriadoPor(Lider criadoPor) {
		CriadoPor = criadoPor;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public Agendamento getAgendamento() {
		return agendamento;
	}

	public void setAgendamento(Agendamento agendamento) {
		this.agendamento = agendamento;
	}

}