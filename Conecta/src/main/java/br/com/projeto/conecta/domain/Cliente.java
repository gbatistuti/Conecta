package br.com.projeto.conecta.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="CLIENTES")
@PrimaryKeyJoinColumn(name="idUsuario")
public class Cliente extends Usuarios{
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Integer id;
	
	private String telefone;
	
	@OneToMany(mappedBy = "cliente")
	private List<Projeto> projeto;

	public Cliente(String email, String senha, Integer codigo, String nome, String telefone) {
		super(email, senha, codigo, nome);
		this.telefone = telefone;
	}
	
//	   @OneToOne
//	   @JoinColumn(name = "FK_USUARIO")
//	   private Usuarios usuarios;

}
