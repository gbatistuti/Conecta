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
@Table(name="HABILIDADES_ESPECIFICAS")
public class HabilidadesEspecificas {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idHabilidadeEspecifico;
	
	private String habilidadeEspecifico;

	@ManyToOne(cascade = CascadeType.ALL)

	@JoinColumn(name = "ID_HABILIDADES_PRINCIPAIS")

	private HabilidadesPrincipais habilidadesPrincipais;

	public HabilidadesEspecificas(Integer idHabilidadeEspecifico, String habilidadeEspecifico) {
		this.idHabilidadeEspecifico = idHabilidadeEspecifico;
		this.habilidadeEspecifico = habilidadeEspecifico;
	}

	public Integer getIdHabilidadeEspecifico() {
		return idHabilidadeEspecifico;
	}

	public void setIdHabilidadeEspecifico(Integer idHabilidadeEspecifico) {
		this.idHabilidadeEspecifico = idHabilidadeEspecifico;
	}

	public String getHabilidadeEspecifico() {
		return habilidadeEspecifico;
	}

	public void setHabilidadeEspecifico(String habilidadeEspecifico) {
		this.habilidadeEspecifico = habilidadeEspecifico;
	}
}
