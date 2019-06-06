package br.com.projeto.conecta.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "HABILIDADES_ESPECIFICAS")
public class HabilidadesEspecificas {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idHabilidadeEspecifica;

	private String nomeHabilidadeEspecifica;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "ID_HABILIDADES_PRINCIPAIS")
	private HabilidadesPrincipais habilidadesPrincipais;

	@ManyToMany(mappedBy = "habilidadesEspecificas")
	private List<Consultor> consultor;

	public HabilidadesEspecificas() {

	}

	public Integer getIdHabilidadeEspecifica() {
		return idHabilidadeEspecifica;
	}

	public void setIdHabilidadeEspecifica(Integer idHabilidadeEspecifica) {
		this.idHabilidadeEspecifica = idHabilidadeEspecifica;
	}

	public String getNomeHabilidadeEspecifica() {
		return nomeHabilidadeEspecifica;
	}

	public void setNomeHabilidadeEspecifica(String nomeHabilidadeEspecifica) {
		this.nomeHabilidadeEspecifica = nomeHabilidadeEspecifica;
	}

	public HabilidadesPrincipais getHabilidadesPrincipais() {
		return habilidadesPrincipais;
	}

	public void setHabilidadesPrincipais(HabilidadesPrincipais habilidadesPrincipais) {
		this.habilidadesPrincipais = habilidadesPrincipais;
	}

	public List<Consultor> getConsultor() {
		return consultor;
	}

	public void setConsultor(List<Consultor> consultor) {
		this.consultor = consultor;
	}

}
