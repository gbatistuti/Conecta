package br.com.projeto.conecta.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "HABILIDADES_PRINCIPAIS")
public class HabilidadesPrincipais {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idHabilidadePrincipal;
	
	private String habilidadePrincipal;

	@OneToMany(mappedBy = "habilidadesPrincipais")
	private List<HabilidadesEspecificas> habilidadesEspecificas;

	public HabilidadesPrincipais(Integer idHabilidadePrincipal, String habilidadePrincipal,
			List<HabilidadesEspecificas> habilidadesEspecificas) {
		this.idHabilidadePrincipal = idHabilidadePrincipal;
		this.habilidadePrincipal = habilidadePrincipal;
		this.habilidadesEspecificas = habilidadesEspecificas;
	}

	public Integer getIdHabilidadePrincipal() {
		return idHabilidadePrincipal;
	}

	public void setIdHabilidadePrincipal(Integer idHabilidadePrincipal) {
		this.idHabilidadePrincipal = idHabilidadePrincipal;
	}

	public String getHabilidadePrincipal() {
		return habilidadePrincipal;
	}

	public void setHabilidadePrincipal(String habilidadePrincipal) {
		this.habilidadePrincipal = habilidadePrincipal;
	}

	public List<HabilidadesEspecificas> getHabilidadesEspecificas() {
		return habilidadesEspecificas;
	}

	public void setHabilidadesEspecificas(List<HabilidadesEspecificas> habilidadesEspecificas) {
		this.habilidadesEspecificas = habilidadesEspecificas;
	}
	
}