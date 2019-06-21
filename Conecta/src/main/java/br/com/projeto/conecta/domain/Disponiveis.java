package br.com.projeto.conecta.domain;

import java.sql.Date;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.List;

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
public class Disponiveis {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idDisponivel;
	
	private LocalTime horaInicio;
	
	private LocalTime horaFim;
	
	private Date data;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "idUsuario")
	private Consultor consultor;
	
	@OneToMany(mappedBy = "disponivel")
	private List<Agendamento> agendamento;
	
	public Disponiveis() {}
	
	public Disponiveis(Integer idDisponivel, LocalTime horaInicio, LocalTime horaFim, Date data, Consultor consultor) {
		this.idDisponivel = idDisponivel;
		this.horaInicio = horaInicio;
		this.horaFim = horaFim;
		this.data = data;
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

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Consultor getConsultor() {
		return consultor;
	}

	public void setConsultor(Consultor consultor) {
		this.consultor = consultor;
	}	

	@PrePersist
	public void data() {
		Calendar calendar = Calendar.getInstance();
		this.data = new Date(calendar.getTime().getTime());
		
	}

}