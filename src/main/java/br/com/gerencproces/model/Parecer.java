package br.com.gerencproces.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.com.gerencproces.model.enums.StatusParecerEnum;

@Entity
@Table(name = "parecer")
@SequenceGenerator(name = "parecer_parecer_id_seq", sequenceName = "parecer_parecer_id_seq", allocationSize = 1)
public class Parecer implements Serializable {
	private static final long serialVersionUID = -3197406533338200682L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "parecer_parecer_id_seq")
	private Integer parecerId;
	private String descricao;
	@Enumerated(EnumType.STRING)
	@NotNull
	private StatusParecerEnum status;
	@ManyToOne
    @JoinColumn(name = "processo_id", nullable = false)
	private Processo processo;
	@ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
	private Usuario usuario;
		
	public Integer getParecerId() {
		return parecerId;
	}

	public void setParecerId(Integer parecerId) {
		this.parecerId = parecerId;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public StatusParecerEnum getStatus() {
		return status;
	}

	public void setStatus(StatusParecerEnum status) {
		this.status = status;
	}

	public Processo getProcesso() {
		return processo;
	}

	public void setProcesso(Processo processo) {
		this.processo = processo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((parecerId == null) ? 0 : parecerId.hashCode());
		result = prime * result + ((processo == null) ? 0 : processo.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
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
		Parecer other = (Parecer) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (parecerId == null) {
			if (other.parecerId != null)
				return false;
		} else if (!parecerId.equals(other.parecerId))
			return false;
		if (processo == null) {
			if (other.processo != null)
				return false;
		} else if (!processo.equals(other.processo))
			return false;
		if (status != other.status)
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}

	public Parecer(Integer parecerId, @NotNull String descricao, StatusParecerEnum status, Processo processo, Usuario usuario) {
		super();
		this.parecerId = parecerId;
		this.descricao = descricao;
		this.status = status;
		this.processo = processo;
		this.usuario = usuario;
	}

	public Parecer() {
		super();	
	}

	
}
