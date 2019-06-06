package br.com.projeto.conecta.domain;

import java.sql.Date;
import java.time.LocalTime;
import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
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
	
	private LocalTime horaInicio;
	
	private LocalTime horaFim;
	
	private Date data;
	
	
	public Alocacoes() {}
	
	public Alocacoes(Agendamento agendamento, Lider criadoPor, LocalTime horaInicio, LocalTime horaFim) {
		this.agendamento = agendamento;
		CriadoPor = criadoPor;
		this.horaInicio = horaInicio;
		this.horaFim = horaFim;
	}

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

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	@PrePersist
	public void data() {
		Calendar calendar = Calendar.getInstance();
		this.data = new Date(calendar.getTime().getTime());
		
	}
}