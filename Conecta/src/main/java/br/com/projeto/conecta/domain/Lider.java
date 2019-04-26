package br.com.projeto.conecta.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "lideres")
public class Lider extends Usuarios{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String unidade;

	public Lider(String email, String senha, Integer codigo, String nome, Integer id, String unidade) {
		super(email, senha, codigo, nome);
		this.id = id;
		this.unidade = unidade;
	}
	
	
}