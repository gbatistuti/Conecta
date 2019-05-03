package br.com.projeto.conecta.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "DEMANDAS")
public class Demanda {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idDemanda;
	private String titulo;
	private String descricao;
	private Date dataHora;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_PROJETO")
	private Projeto projeto;

	public Demanda(Integer idDemanda, String titulo, String descricao, Date dataHora, Projeto projeto) {
		this.idDemanda = idDemanda;
		this.titulo = titulo;
		this.descricao = descricao;
		this.dataHora = dataHora;
		this.projeto = projeto;
	}

	public Integer getIdDemanda() {
		return idDemanda;
	}

	public void setIdDemanda(Integer idDemanda) {
		this.idDemanda = idDemanda;
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

	public Date getDataHora() {
		return dataHora;
	}

	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}
	
	
	
	
}
