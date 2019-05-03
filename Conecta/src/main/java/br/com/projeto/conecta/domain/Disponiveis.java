package br.com.projeto.conecta.domain;

import java.sql.Time;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DISPONIVEIS")
public class Disponiveis {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idDisponivel;
	
	private Time horaInicio;
	
	private Time horaFim;
	
	private Date data;

	public Disponiveis(Integer idDisponivel, Time horaInicio, Time horaFim, Date data) {
		this.idDisponivel = idDisponivel;
		this.horaInicio = horaInicio;
		this.horaFim = horaFim;
		this.data = data;
	}

	public Integer getIdDisponivel() {
		return idDisponivel;
	}

	public void setIdDisponivel(Integer idDisponivel) {
		this.idDisponivel = idDisponivel;
	}

	public Time getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Time horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Time getHoraFim() {
		return horaFim;
	}

	public void setHoraFim(Time horaFim) {
		this.horaFim = horaFim;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	

}