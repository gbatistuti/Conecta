package br.com.projeto.conecta.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "lideres")
@PrimaryKeyJoinColumn(name="idUsuario")
public class Lider extends Usuarios{
	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Integer id;
	
	private String unidade;


	public Lider(String email, String senha, Integer codigo, String nome, String unidade, String tipo) {
		super(email, senha, codigo, nome);
		this.unidade = unidade;
	}

//	   @OneToOne
//	   @JoinColumn(name = "FK_USUARIO")
//	   private Usuarios usuarios;
	
}