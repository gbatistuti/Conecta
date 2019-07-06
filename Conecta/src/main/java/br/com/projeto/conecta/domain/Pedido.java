package br.com.projeto.conecta.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	private int horasContratadas;
	private String status;
	private String origem;
	private boolean candidatura;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_PROJETO")
	private Projeto projeto;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_CRIADO_POR")
	private Usuario criadoPor;

	public Pedido() {
	}

	public Pedido(Integer idPedido, String titulo, String descricao, int horasContratadas, String status, String origem, Projeto projeto, Usuario criadoPor) {
		this.idPedido = idPedido;
		this.titulo = titulo;
		this.descricao = descricao;
		this.horasContratadas = horasContratadas;
		this.status = status;
		this.origem = origem;
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

	public int getHorasContratadas() {
		return horasContratadas;
	}

	public void setHorasContratadas(int horasContratadas) {
		this.horasContratadas = horasContratadas;
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

	public Usuario getCriadoPor() {
		return criadoPor;
	}
	
	public void setCriadoPor(Usuario criadoPor) {
		this.criadoPor = criadoPor;
	}

	public boolean isCandidatura() {
		return candidatura;
	}

	public void setCandidatura(boolean candidatura) {
		this.candidatura = candidatura;
	}

	@PrePersist
	public void prePersist() {
		this.status = "Aguardando";
		this.candidatura = false;
	}
}
