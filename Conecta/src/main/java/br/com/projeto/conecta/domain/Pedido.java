package br.com.projeto.conecta.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PEDIDOS")
public class Pedido {

	@Id
	@GeneratedValue
	private Integer idPedido;

	private String titulo;

	private String descricao;

	private String Status;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_PROJETO")
	private Projeto projeto;

}