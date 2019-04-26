package br.com.projeto.conecta.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="recursos")
@PrimaryKeyJoinColumn(name="idUsuario")
public class Consultor extends Usuarios{

//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Integer id;
	
	private String unidade;
	private String cargo;
	private Float preco;

	public Consultor(String email, String senha, Integer codigo, String nome, String unidade, String cargo, Float preco) {
		super(email, senha, codigo, nome);
		this.unidade = unidade;
		this.cargo = cargo;
		this.preco = preco;
	}
	

	
//	   @OneToOne
//	   @JoinColumn(name = "FK_USUARIO")
//	   private Usuarios usuarios;
	
}
