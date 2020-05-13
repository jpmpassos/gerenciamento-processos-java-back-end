package br.com.gerencproces.repository.query;

import java.util.List;

import br.com.gerencproces.model.Processo;

public interface ProcessoRepositoryQuery {
	public List<Processo> listarProcessosPendentes(Integer usuarioId);
}
