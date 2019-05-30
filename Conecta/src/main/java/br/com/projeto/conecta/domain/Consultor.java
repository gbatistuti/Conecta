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
public class Consultor extends Usuarios {

	private String unidade;
	private String cargo;
	private Float creditosPorHora;

	@OneToMany(mappedBy = "consultor")
	private List<Disponiveis> disponiveis;

	@ManyToMany
	@JoinTable(name = "HABILIDADES_ESPECIFICAS_TEM_RECURSOS", joinColumns = {
			@JoinColumn(name = "idUsuario") }, inverseJoinColumns = { @JoinColumn(name = "idHabilidadeEspecifica") })
	private List<HabilidadesEspecificas> habilidadesEspecificas;

	public Consultor() {
	}
	
	public Consultor(String email, String senha, String codigo, String nome, List<Grupo> grupo, String unidade, String cargo, Float creditosPorHora) {
		super(email, senha, codigo, nome, grupo);
		this.unidade = unidade;
		this.cargo = cargo;
		this.creditosPorHora = creditosPorHora;
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

	public List<Disponiveis> getDisponiveis() {
		return disponiveis;
	}

	public void setDisponiveis(List<Disponiveis> disponiveis) {
		this.disponiveis = disponiveis;
	}

	public List<HabilidadesEspecificas> getHabilidadesEspecificas() {
		return habilidadesEspecificas;
	}

	public void setHabilidadesEspecificas(List<HabilidadesEspecificas> habilidadesEspecificas) {
		this.habilidadesEspecificas = habilidadesEspecificas;
	}
}
