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
import br.com.gerencproces.model.Parecer;
import br.com.gerencproces.service.ParecerService;

@RestController
@RequestMapping("/parecer")
public class ParecerResource {

	@Autowired
	private ApplicationEventPublisher publisher;
	@Autowired
	private ParecerService parecerService;

	@CrossOrigin
	@PostMapping
	public ResponseEntity<Parecer> cria(@Valid @RequestBody Parecer parecer, HttpServletResponse response) throws NoSuchAlgorithmException {
		Parecer parecerSalvo = parecerService.salvar(parecer);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, parecerSalvo.getParecerId()));

		return ResponseEntity.status(HttpStatus.CREATED).body(parecerSalvo);
	}

	@CrossOrigin
	@PutMapping("{codigo}")
	public ResponseEntity<Parecer> atualizar(@PathVariable Integer codigo, @RequestBody Parecer parecer) {

		return ResponseEntity.ok(parecerService.atualizar(codigo, parecer));
	}

	@CrossOrigin
	@GetMapping("/{codigo}")
	public ResponseEntity<Parecer> buscarPeloCodigo(@PathVariable Integer codigo, HttpServletResponse response) {
		Parecer parecer = parecerService.buscarParecerPeloCodigo(codigo);

		if (parecer == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(parecer);
	}

	@CrossOrigin
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Integer codigo) {
		parecerService.deletar(codigo);
	}
	
	@CrossOrigin
	@GetMapping
	public List<Parecer> listarTodos() {	
		return parecerService.listarTodos();
	}
	
	@CrossOrigin
	@GetMapping("/porprocesso")
	public List<Parecer> listarPorProcesso(Integer processoId) {	
		return parecerService.listarPorProcesso(processoId);
	}
	
	@CrossOrigin
	@GetMapping("/pendenteporusuario")
	public List<Parecer> listarPendentePorUsuario(Integer usuarioId) {	
		return parecerService.listarPendentePorUsuario(usuarioId);
	}

}