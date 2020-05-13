package br.com.gerencproces.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "usuario")
@SequenceGenerator(name = "usuario_usuario_id_seq", sequenceName = "usuario_usuario_id_seq", allocationSize = 1)
public class Usuario implements Serializable {
	private static final long serialVersionUID = -3197406533338200682L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_usuario_id_seq")
	private Integer usuarioId;
	@NotNull
	private String nome;
	@NotNull
	private Boolean admin;
	@NotNull
	private Boolean triador;
	@NotNull
	private Boolean finalizador;
	@NotNull
	private String login;
	@NotNull
	private String senha;

	public Integer getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	public Boolean getTriador() {
		return triador;
	}

	public void setTriador(Boolean triador) {
		this.triador = triador;
	}

	public Boolean getFinalizador() {
		return finalizador;
	}

	public void setFinalizador(Boolean finalizador) {
		this.finalizador = finalizador;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((admin == null) ? 0 : admin.hashCode());
		result = prime * result + ((finalizador == null) ? 0 : finalizador.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
		result = prime * result + ((triador == null) ? 0 : triador.hashCode());
		result = prime * result + ((usuarioId == null) ? 0 : usuarioId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (admin == null) {
			if (other.admin != null)
				return false;
		} else if (!admin.equals(other.admin))
			return false;
		if (finalizador == null) {
			if (other.finalizador != null)
				return false;
		} else if (!finalizador.equals(other.finalizador))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		if (triador == null) {
			if (other.triador != null)
				return false;
		} else if (!triador.equals(other.triador))
			return false;
		if (usuarioId == null) {
			if (other.usuarioId != null)
				return false;
		} else if (!usuarioId.equals(other.usuarioId))
			return false;
		return true;
	}

	public Usuario(Integer usuarioId, @NotNull String nome, @NotNull Boolean admin, @NotNull Boolean triador,
			@NotNull Boolean finalizador, @NotNull String login, @NotNull String senha) {
		super();
		this.usuarioId = usuarioId;
		this.nome = nome;
		this.admin = admin;
		this.triador = triador;
		this.finalizador = finalizador;
		this.login = login;
		this.senha = senha;
	}

	public Usuario() {
		super();
	}
	
	public Usuario(final Usuario usuario) {
		this.admin = usuario.admin;
		this.finalizador = usuario.finalizador;
		this.login = usuario.login;
		this.nome = usuario.nome;
		this.senha = usuario.nome;
		this.triador = usuario.triador;
		this.usuarioId = usuario.usuarioId;
		
	}

}
