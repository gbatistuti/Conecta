package br.com.projeto.conecta.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name = "DISPONIVEIS")
public class Disponivel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idDisponivel;
	
	@Column(nullable = false)
	private LocalTime horaInicio;
	
	@Column(nullable = false)
	private LocalTime horaFim;

	@Column(nullable = false)
	private LocalDate logData;
	
	@Column(nullable = false)
	private LocalTime logHora;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "idUsuario")
	private Consultor consultor;
	
	@OneToMany(mappedBy = "disponivel")
	private List<Agendamento> agendamento;
	
	public Disponivel() {}
	
	public Disponivel(Integer idDisponivel, LocalTime horaInicio, LocalTime horaFim, Consultor consultor) {
		this.idDisponivel = idDisponivel;
		this.horaInicio = horaInicio;
		this.horaFim = horaFim;
		this.consultor = consultor;
	}

	public Integer getIdDisponivel() {
		return idDisponivel;
	}

	public void setIdDisponivel(Integer idDisponivel) {
		this.idDisponivel = idDisponivel;
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


	public Consultor getConsultor() {
		return consultor;
	}

	public void setConsultor(Consultor consultor) {
		this.consultor = consultor;
	}	
	
	public List<Agendamento> getAgendamento() {
		return agendamento;
	}

	public void setAgendamento(List<Agendamento> agendamento) {
		this.agendamento = agendamento;
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