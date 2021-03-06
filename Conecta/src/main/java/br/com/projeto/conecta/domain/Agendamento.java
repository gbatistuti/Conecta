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
@Table(name = "AGENDAMENTOS")
public class Agendamento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idAgendamento;
	
	@Column(nullable = false)
	private LocalDate logData;
	
	@Column(nullable = false)
	private LocalTime logHora;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "ID_DISPONIVEL")
	private Disponivel disponivel;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "ID_CRIADO_POR")
	private Usuario criadoPor;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_PEDIDO")
	private Pedido pedido;
	
	public Agendamento() {
	}

	public Agendamento(Disponivel disponivel, Usuario criadoPor, Pedido pedido) {
		this.disponivel= disponivel;
		this.criadoPor = criadoPor;
		this.pedido = pedido;
	}
	
	public Integer getIdAgendamento() {
		return idAgendamento;
	}

	public void setIdAgendamento(Integer idAgendamento) {
		this.idAgendamento = idAgendamento;
	}

	public Disponivel getDisponivel() {
		return disponivel;
	}

	public void setDisponivel(Disponivel disponivel) {
		this.disponivel = disponivel;
	}

	public Usuario getCriadoPor() {
		return criadoPor;
	}

	public void setCriadoPor(Usuario criadoPor) {
		this.criadoPor = criadoPor;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
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
