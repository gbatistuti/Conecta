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
@Table(name = "AGENDAMENTOS")
public class Agendamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idAgendamento;

	@ManyToOne
	@JoinColumn(name = "ID_CONSULTOR")
	private Consultor consultor;
	
	@ManyToOne
	@JoinColumn(name = "ID_CRIADO_POR")
	private Usuarios criadoPor;
	
	@OneToOne
	@JoinColumn(name = "ID_PEDIDO")
	private Pedido pedido;

	public Agendamento() {
	}

	public Agendamento(Consultor consultor, Usuarios criadoPor, Pedido pedido) {
		this.consultor = consultor;
		this.criadoPor = criadoPor;
		this.pedido = pedido;
	}
	
	public Integer getIdAgendamento() {
		return idAgendamento;
	}

	public void setIdAgendamento(Integer idAgendamento) {
		this.idAgendamento = idAgendamento;
	}

	public Consultor getConsultor() {
		return consultor;
	}

	public void setConsultor(Consultor consultor) {
		this.consultor = consultor;
	}

	public Usuarios getCriadoPor() {
		return criadoPor;
	}

	public void setCriadoPor(Usuarios criadoPor) {
		this.criadoPor = criadoPor;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
}
