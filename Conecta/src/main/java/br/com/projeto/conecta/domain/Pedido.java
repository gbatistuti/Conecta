package br.com.projeto.conecta.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PEDIDOS")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idPedido;

	private String titulo;

	private String descricao;

	private String Status;
	
	private int sugestaoDeHoras;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_PROJETO")
	private Projeto projeto;

	public Pedido() {
	};

	public Pedido(Integer idPedido, String titulo, String descricao, String status, Projeto projeto) {
		this.idPedido = idPedido;
		this.titulo = titulo;
		this.descricao = descricao;
		Status = status;
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
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
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
	
	

}