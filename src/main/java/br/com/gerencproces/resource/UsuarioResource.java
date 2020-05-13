package br.com.gerencproces.resource;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.gerencproces.event.RecursoCriadoEvent;
import br.com.gerencproces.model.Usuario;
import br.com.gerencproces.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioResource {

	@Autowired
	private ApplicationEventPublisher publisher;
	@Autowired
	private UsuarioService usuarioService;

	@CrossOrigin
	@GetMapping
	public List<Usuario> listar() {
		return usuarioService.listarTodos();
	}

	@CrossOrigin
	@PostMapping
	public ResponseEntity<Usuario> cria(@Valid @RequestBody Usuario usuario, HttpServletResponse response) throws NoSuchAlgorithmException {
		Usuario usuarioSalvo = usuarioService.salvar(usuario);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, usuarioSalvo.getUsuarioId()));

		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioSalvo);
	}

	@CrossOrigin
	@PutMapping("{codigo}")
	public ResponseEntity<Usuario> atualizar(@PathVariable Integer codigo, @RequestBody Usuario usuario) {

		return ResponseEntity.ok(usuarioService.atualizar(codigo, usuario));
	}

	@CrossOrigin
	@GetMapping("/{codigo}")
	public ResponseEntity<Usuario> buscarPeloCodigo(@PathVariable Integer codigo, HttpServletResponse response) {
		Usuario usuario = usuarioService.buscarUsuarioPeloCodigo(codigo);

		if (usuario == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(usuario);
	}

	@CrossOrigin
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Integer codigo) {
		usuarioService.deletar(codigo);
	}
	
	@CrossOrigin
	public List<Usuario> listarTodos() {	
		return usuarioService.listarTodos();
	}
	
	@CrossOrigin
	@GetMapping("/finalizador")
	public List<Usuario> listarUsuariosFinalizadores() {	
		return usuarioService.listarUsuariosFinalizadores();
	}
	
	@CrossOrigin
	@GetMapping("/autenticacao")
	public Usuario autenticacao(String login, String senha) throws NoSuchAlgorithmException {	
		return usuarioService.autenticar(login, senha);
	}

}