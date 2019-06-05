package br.com.projeto.conecta.domain;

import java.sql.Date;
import java.util.Calendar;

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
@Table(name = "RECUSADOS")
public class Recusado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idRecusado;

	private String motivo;
	
	private Date data;

	@OneToOne
	@JoinColumn(name = "ID_AGENDAMENTO")
	private Agendamento agendamento;
	
	@ManyToOne
	@JoinColumn(name = "ID_CRIADO_POR")
	private Usuarios criadoPor;

	public Integer getIdRecusado() {
		return idRecusado;
	}

	public void setIdRecusado(Integer idRecusado) {
		this.idRecusado = idRecusado;
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

	public Usuarios getCriadoPor() {
		return criadoPor;
	}

	public void setCriadoPor(Usuarios criadoPor) {
		this.criadoPor = criadoPor;
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