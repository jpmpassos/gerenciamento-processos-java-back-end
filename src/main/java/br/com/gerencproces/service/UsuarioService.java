package br.com.gerencproces.service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.gerencproces.model.Usuario;
import br.com.gerencproces.repository.UsuarioRepository;
import br.com.gerencproces.util.AuthUtil;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public Usuario salvar(Usuario usuario) throws NoSuchAlgorithmException {

		usuario.setSenha(AuthUtil.generateHash(usuario.getSenha()));

		return usuarioRepository.save(usuario);
	}

	public Usuario atualizar(Integer codigo, Usuario usuario) {
		Usuario usuarioBanco = buscarUsuarioPeloCodigo(codigo);
		BeanUtils.copyProperties(usuario, usuarioBanco, "usuarioId", "senha", "login");
		return usuarioRepository.save(usuarioBanco);
	}

	public Usuario buscarUsuarioPeloCodigo(Integer codigo) {
		Usuario usuarioBanco = usuarioRepository.findById(codigo).orElse(null);
		if (usuarioBanco == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return usuarioBanco;
	}

	public List<Usuario> listarTodos() {
		return usuarioRepository.findAll(new Sort(Sort.Direction.ASC, "nome"));
	}

	public List<Usuario> listarUsuariosFinalizadores() {
		return usuarioRepository.listarUsuariosFinalizadores();
	}

	public void deletar(Integer codigo) {
		usuarioRepository.deleteById(codigo);
	}

	public Usuario autenticar(String login, String senha) throws NoSuchAlgorithmException {
		Usuario usuario = usuarioRepository.findByLogin(login).orElse(null);
		if (usuario != null) {
			if (usuario.getSenha().equals(AuthUtil.generateHash(senha))) {
				return usuario;
			}			
		}
		return null;
	}

}
