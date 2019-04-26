package br.com.projeto.conecta.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="clientes")
public class Cliente extends Usuarios{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String telefone;

	public Cliente(String email, String senha, Integer codigo, String nome, Integer id, String telefone) {
		super(email, senha, codigo, nome);
		this.id = id;
		this.telefone = telefone;
	}

}
