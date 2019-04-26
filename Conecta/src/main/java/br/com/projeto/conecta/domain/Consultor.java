package br.com.projeto.conecta.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="recursos")
public class Consultor extends Usuarios{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String unidade;
	private String cargo;
	private Float preco;
	
	public Consultor(String email, String senha, Integer codigo, String nome, Integer id, String unidade, String cargo,
			Float preco) {
		super(email, senha, codigo, nome);
		this.id = id;
		this.unidade = unidade;
		this.cargo = cargo;
		this.preco = preco;
	}
	
}
