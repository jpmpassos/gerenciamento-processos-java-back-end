package br.com.gerencproces.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gerencproces.model.Processo;
import br.com.gerencproces.repository.query.ProcessoRepositoryQuery;

public interface ProcessoRepository extends JpaRepository<Processo, Integer>, ProcessoRepositoryQuery {

}
