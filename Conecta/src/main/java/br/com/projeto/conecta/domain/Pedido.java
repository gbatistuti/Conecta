package br.com.projeto.conecta.domain;

import javax.persistence.CascadeType;
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
@Table(name = "PEDIDOS")
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idPedido;

	private String titulo;

	private String descricao;

	private int sugestaoDeHoras;

	private String status;
	
	private String origem;
	
	private boolean candidatura;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_PROJETO")
	private Projeto projeto;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_CRIADO_POR")
	private Usuarios criadoPor;
	
	@OneToOne(mappedBy = "pedido", fetch=FetchType.LAZY)
	private Agendamento agendamento;

	public Pedido() {
	}

	public Pedido(Integer idPedido, String titulo, String descricao, int sugestaoDeHoras, String status, String origem,
			boolean candidatura, Projeto projeto, Usuarios criadoPor) {
		this.idPedido = idPedido;
		this.titulo = titulo;
		this.descricao = descricao;
		this.sugestaoDeHoras = sugestaoDeHoras;
		this.status = status;
		this.origem = origem;
		this.candidatura = candidatura;
		this.projeto = projeto;
		this.criadoPor = criadoPor;
	}
	
	public Integer getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getSugestaoDeHoras() {
		return sugestaoDeHoras;
	}

	public void setSugestaoDeHoras(int sugestaoDeHoras) {
		this.sugestaoDeHoras = sugestaoDeHoras;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}
	
	public Projeto getProjeto() {
		return projeto;
	}
	
	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public Usuarios getCriadoPor() {
		return criadoPor;
	}
	
	public void setCriadoPor(Usuarios criadoPor) {
		this.criadoPor = criadoPor;
	}
	
	public Agendamento getAgendamento() {
		return agendamento;
	}

	public void setAgendamento(Agendamento agendamento) {
		this.agendamento = agendamento;
	}

	public boolean isCandidatura() {
		return candidatura;
	}

	public void setCandidatura(boolean candidatura) {
		this.candidatura = candidatura;
	}

	@PrePersist
	public void prePersist() {
		this.status = "aguardando";
		this.candidatura = false;
	}
}
