package br.com.projeto.conecta.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "USUARIOS")
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario implements Serializable, UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idUsuario;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false, unique = true)
	private String email;
	
	@Column(nullable = false)
	private String senha;
	
	@Column(nullable = false, unique = true)
	private String codigo;

	@ManyToMany
	@JoinTable(name = "USUARIOS_TEM_GRUPOS", joinColumns = @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario"), inverseJoinColumns = @JoinColumn(name = "idGrupo", referencedColumnName = "nomeGrupo"))
	private List<Grupo> grupo;
	
	@OneToMany(mappedBy = "criadoPor")
	private List<Agendamento> agendamentos;
	
	@OneToMany(mappedBy = "criadoPor")
	private List<Pedido> pedidos;
	
	@OneToMany(mappedBy = "criadoPor")
	private List<Reprovado> recusado;
	
	public Usuario() {
	}
	
	public Usuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	public Usuario(String email, String senha, String codigo, String nome, List<Grupo> grupo) {
		this.email = email;
		this.senha = senha;
		this.codigo = codigo;
		this.nome = nome;
		this.grupo = grupo;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Grupo> getGrupo() {
		return grupo;
	}

	public void setGrupo(List<Grupo> grupo) {
		this.grupo = grupo;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public List<Reprovado> getRecusado() {
		return recusado;
	}

	public void setRecusado(List<Reprovado> recusado) {
		this.recusado = recusado;
	}
	
	public List<Agendamento> getAgendamentos() {
		return agendamentos;
	}
	
	public void setAgendamentos(List<Agendamento> agendamentos) {
		this.agendamentos = agendamentos;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.grupo;
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
}
