package br.com.projeto.conecta.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "PROJETOS")
public class Projeto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idProjeto;
	private String codigoProjeto;
	private String nome;
	private String produto;
	private String modulo;
	private String coordenador;
	private Float qtdCreditos;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "ID_CLIENTE")
	private Cliente cliente;

	@OneToMany(mappedBy = "projeto")
	private List<Pedido> pedido;

	public Projeto() {
	}

	public Projeto(Integer idProjeto, String codigoProjeto, String nome, String produto, String modulo,
			String coordenador, Float qtdCreditos, Cliente cliente) {
		this.idProjeto = idProjeto;
		this.codigoProjeto = codigoProjeto;
		this.nome = nome;
		this.produto = produto;
		this.modulo = modulo;
		this.coordenador = coordenador;
		this.qtdCreditos = qtdCreditos;
		this.cliente = cliente;
	}

	public Integer getIdProjeto() {
		return idProjeto;
	}

	public void setIdProjeto(Integer idProjeto) {
		this.idProjeto = idProjeto;
	}

	public String getCodigoProjeto() {
		return codigoProjeto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCodigoProjeto(String codigoProjeto) {
		this.codigoProjeto = codigoProjeto;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public String getModulo() {
		return modulo;
	}

	public void setModulo(String modulo) {
		this.modulo = modulo;
	}

	public String getCoordenador() {
		return coordenador;
	}

	public void setCoordenador(String coordenador) {
		this.coordenador = coordenador;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Pedido> getPedido() {
		return pedido;
	}

	public void setPedido(List<Pedido> pedido) {
		this.pedido = pedido;
	}

	public Float getQtdCreditos() {
		return qtdCreditos;
	}

	public void setQtdCreditos(Float qtdCreditos) {
		this.qtdCreditos = qtdCreditos;
	}
}