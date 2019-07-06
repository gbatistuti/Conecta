package br.com.projeto.conecta.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "HABILIDADES_PRINCIPAIS")
public class HabilidadePrincipal {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idHabilidadePrincipal;
	
	@Column(nullable = false)
	private String nomeHabilidadePrincipal;

	@OneToMany(mappedBy = "habilidadesPrincipais")
	private List<HabilidadeEspecifica> habilidadesEspecificas;

	public Integer getIdHabilidadePrincipal() {
		return idHabilidadePrincipal;
	}

	public void setIdHabilidadePrincipal(Integer idHabilidadePrincipal) {
		this.idHabilidadePrincipal = idHabilidadePrincipal;
	}

	public String getNomeHabilidadePrincipal() {
		return nomeHabilidadePrincipal;
	}

	public void setNomeHabilidadePrincipal(String nomeHabilidadePrincipal) {
		this.nomeHabilidadePrincipal = nomeHabilidadePrincipal;
	}

	public List<HabilidadeEspecifica> getHabilidadesEspecificas() {
		return habilidadesEspecificas;
	}

	public void setHabilidadesEspecificas(List<HabilidadeEspecifica> habilidadesEspecificas) {
		this.habilidadesEspecificas = habilidadesEspecificas;
	}

}