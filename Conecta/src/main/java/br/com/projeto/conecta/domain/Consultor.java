package br.com.projeto.conecta.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "CONSULTORES")
@PrimaryKeyJoinColumn(name = "idUsuario")
public class Consultor extends Usuario {

	private String unidade;
	private String cargo;
	private Float creditosPorHora;

	@OneToMany(mappedBy = "consultor")
	private List<Disponivel> disponiveis;

	@ManyToMany
	@JoinTable(name = "HABILIDADES_ESPECIFICAS_TEM_RECURSOS", joinColumns = {
			@JoinColumn(name = "idUsuario") }, inverseJoinColumns = { @JoinColumn(name = "idHabilidadeEspecifica") })
	private List<HabilidadeEspecifica> habilidadesEspecificas;

	public Consultor() {
	}
	
	public Consultor(String email, String senha, String codigo, String nome, List<Grupo> grupo, String unidade, String cargo, Float creditosPorHora) {
		super(email, senha, codigo, nome, grupo);
		this.unidade = unidade;
		this.cargo = cargo;
		this.creditosPorHora = creditosPorHora;
	}

	public Consultor(Integer idUsuario) {
		super(idUsuario);
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public Float getCreditosPorHora() {
		return creditosPorHora;
	}

	public void setCreditosPorHora(Float creditosPorHora) {
		this.creditosPorHora = creditosPorHora;
	}

	public List<Disponivel> getDisponiveis() {
		return disponiveis;
	}

	public void setDisponiveis(List<Disponivel> disponiveis) {
		this.disponiveis = disponiveis;
	}

	public List<HabilidadeEspecifica> getHabilidadesEspecificas() {
		return habilidadesEspecificas;
	}

	public void setHabilidadesEspecificas(List<HabilidadeEspecifica> habilidadesEspecificas) {
		this.habilidadesEspecificas = habilidadesEspecificas;
	}
}
