package br.com.gerencproces.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.gerencproces.model.Parecer;

public interface ParecerRepository extends JpaRepository<Parecer, Integer> {
	@Query(value = "SELECT * from parecer p where p.processo_id = ?1 order by p.parecer_id ", nativeQuery = true)
	public List<Parecer> listarPorProcesso(Integer processoId);
	
	@Query(value = "SELECT * from parecer p where p.status = 'PENDENTE' and p.usuario_id = ?1 order by p.parecer_id ", nativeQuery = true)
	public List<Parecer> listarPendentePorUsuario(Integer usuarioId);
}
