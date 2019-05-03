package br.com.projeto.conecta.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
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
	private Integer codigoProjeto;
	private String produto;
	private String modulo;
	private String coordenador;
	private Integer qtdBho;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_CLIENTE")
	private Cliente cliente;
	
	@OneToMany(mappedBy = "projeto")
	private List<Demanda> demanda;

	public Projeto(Integer idProjeto, Integer codigoProjeto, String produto, String modulo, String coordenador,
			Integer qtdBho, Cliente cliente, List<Demanda> demanda) {
		this.idProjeto = idProjeto;
		this.codigoProjeto = codigoProjeto;
		this.produto = produto;
		this.modulo = modulo;
		this.coordenador = coordenador;
		this.qtdBho = qtdBho;
		this.cliente = cliente;
		this.demanda = demanda;
	}

	public Integer getIdProjeto() {
		return idProjeto;
	}

	public void setIdProjeto(Integer idProjeto) {
		this.idProjeto = idProjeto;
	}

	public Integer getCodigoProjeto() {
		return codigoProjeto;
	}

	public void setCodigoProjeto(Integer codigoProjeto) {
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

	public Integer getQtdBho() {
		return qtdBho;
	}

	public void setQtdBho(Integer qtdBho) {
		this.qtdBho = qtdBho;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Demanda> getDemanda() {
		return demanda;
	}

	public void setDemanda(List<Demanda> demanda) {
		this.demanda = demanda;
	}
	
	
	

}
