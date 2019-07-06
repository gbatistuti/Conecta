package br.com.projeto.conecta.domain;

import java.util.List;

import javax.persistence.Column;
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
public class HabilidadeEspecifica {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idHabilidadeEspecifica;

	@Column(nullable = false)
	private String nomeHabilidadeEspecifica;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "ID_HABILIDADES_PRINCIPAIS")
	private HabilidadePrincipal habilidadesPrincipais;

	@ManyToMany(mappedBy = "habilidadesEspecificas")
	private List<Consultor> consultor;

	public HabilidadeEspecifica() {

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

	public HabilidadePrincipal getHabilidadesPrincipais() {
		return habilidadesPrincipais;
	}

	public void setHabilidadesPrincipais(HabilidadePrincipal habilidadesPrincipais) {
		this.habilidadesPrincipais = habilidadesPrincipais;
	}

	public List<Consultor> getConsultor() {
		return consultor;
	}

	public void setConsultor(List<Consultor> consultor) {
		this.consultor = consultor;
	}

}
