package br.com.gerencproces.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.gerencproces.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	@Query(value = "SELECT * from usuario u where u.finalizador order by u.nome ", nativeQuery = true)
	public List<Usuario> listarUsuariosFinalizadores();

	Optional<Usuario> findByLogin(String username);
}
