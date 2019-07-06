package br.com.projeto.conecta.domain;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name = "REPROVADOS")
public class Reprovado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idReprovado;

	@Column(nullable = false)
	private String motivo;
	
	@Column(nullable = false)
	private LocalDate logData;
	
	@Column(nullable = false)
	private LocalTime logHora;

	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "ID_AGENDAMENTO")
	private Agendamento agendamento;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "ID_CRIADO_POR")
	private Usuario criadoPor;
	
	public Reprovado() {
	}
	
	public Reprovado(String motivo, Agendamento agendamento, Usuario criadoPor) {
		this.motivo = motivo;
		this.agendamento = agendamento;
		this.criadoPor = criadoPor;
	}

	public Integer getIdReprovado() {
		return idReprovado;
	}

	public void setIdRecusado(Integer idRecusado) {
		this.idReprovado = idRecusado;
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

	public Usuario getCriadoPor() {
		return criadoPor;
	}

	public void setCriadoPor(Usuario criadoPor) {
		this.criadoPor = criadoPor;
	}
	
	public LocalDate getLogData() {
		return logData;
	}

	public LocalTime getLogHora() {
		return logHora;
	}

	@PrePersist
	public void prePersist() {
		this.logData = LocalDate.now();
		this.logHora = LocalTime.now();
	}
}