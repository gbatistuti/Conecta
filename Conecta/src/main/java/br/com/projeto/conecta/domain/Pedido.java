package br.com.projeto.conecta.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
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

	private String status;
	
	private int sugestaoDeHoras;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_PROJETO")
	private Projeto projeto;

	public Pedido() {
	};

	public Pedido(String titulo, String descricao, String status, Projeto projeto) {
		status = "aguardando";
		this.titulo = titulo;
		this.descricao = descricao;
		this.status = status;
		this.projeto = projeto;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public int getSugestaoDeHoras() {
		return sugestaoDeHoras;
	}

	public void setSugestaoDeHoras(int sugestaoDeHoras) {
		this.sugestaoDeHoras = sugestaoDeHoras;
	}
	
	@PrePersist
	public void status() {
		this.status = "aguardando";
	}

}