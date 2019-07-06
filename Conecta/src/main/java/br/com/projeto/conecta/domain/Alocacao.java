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
@Table(name = "ALOCACOES")
public class Alocacao {

	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idAlocacao;
	
	@Column(nullable = true, length = 200)
	private String motivo;
	
	@Column(nullable = false)
	private LocalTime horaInicio;
	
	@Column(nullable = false)
	private LocalTime horaFim;
	
	@Column(nullable = false)
	private LocalDate logData;
	
	@Column(nullable = false)
	private LocalTime logHora;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "ID_AGENDAMENTO")
	private Agendamento agendamento;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "ID_LIDER")
	private Lider lider;
	
	
	public Alocacao() {}
	
	public Alocacao(Agendamento agendamento, Lider criadoPor, LocalTime horaInicio, LocalTime horaFim, String motivo) {
		this.agendamento = agendamento;
		this.lider = criadoPor;
		this.horaInicio = horaInicio;
		this.horaFim = horaFim;
		this.motivo = motivo;
	}

	public Integer getIdAlocacao() {
		return idAlocacao;
	}

	public void setIdAlocacao(Integer idAlocacao) {
		this.idAlocacao = idAlocacao;
	}

	public Lider getCriadoPor() {
		return lider;
	}

	public void setCriadoPor(Lider criadoPor) {
		this.lider = criadoPor;
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

	public LocalTime getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(LocalTime horaInicio) {
		this.horaInicio = horaInicio;
	}

	public LocalTime getHoraFim() {
		return horaFim;
	}

	public void setHoraFim(LocalTime horaFim) {
		this.horaFim = horaFim;
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