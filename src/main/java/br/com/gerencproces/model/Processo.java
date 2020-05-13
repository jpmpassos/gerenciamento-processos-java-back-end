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
@Table(name = "processo")
@SequenceGenerator(name = "processo_processo_id_seq", sequenceName = "processo_processo_id_seq", allocationSize = 1)
public class Processo implements Serializable {
	private static final long serialVersionUID = -3197406533338200682L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "processo_processo_id_seq")
	private Integer processoId;
	@NotNull
	private String titulo;
	private String descricao;

	public Integer getProcessoId() {
		return processoId;
	}

	public void setProcessoId(Integer processoId) {
		this.processoId = processoId;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((processoId == null) ? 0 : processoId.hashCode());
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
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
		Processo other = (Processo) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (processoId == null) {
			if (other.processoId != null)
				return false;
		} else if (!processoId.equals(other.processoId))
			return false;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		return true;
	}

	public Processo(Integer processoId, @NotNull String titulo, String descricao) {
		super();
		this.processoId = processoId;
		this.titulo = titulo;
		this.descricao = descricao;
	}

	public Processo() {
		super();
	}

}
