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
	
	@man
	
	
}