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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.gerencproces.event.RecursoCriadoEvent;
import br.com.gerencproces.model.Processo;
import br.com.gerencproces.service.ProcessoService;

@RestController
@RequestMapping("/processo")
public class ProcessoResource {

	@Autowired
	private ApplicationEventPublisher publisher;
	@Autowired
	private ProcessoService processoService;

	@CrossOrigin
	@GetMapping
	public List<Processo> listar() {
		return processoService.listarTodos();
	}

	@CrossOrigin
	@PostMapping
	public ResponseEntity<Processo> cria(@Valid @RequestBody Processo processo, HttpServletResponse response) throws NoSuchAlgorithmException {
		Processo processoSalvo = processoService.salvar(processo);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, processoSalvo.getProcessoId()));

		return ResponseEntity.status(HttpStatus.CREATED).body(processoSalvo);
	}

	@CrossOrigin
	@PutMapping("{codigo}")
	public ResponseEntity<Processo> atualizar(@PathVariable Integer codigo, @RequestBody Processo processo) {

		return ResponseEntity.ok(processoService.atualizar(codigo, processo));
	}

	@CrossOrigin
	@GetMapping("/{codigo}")
	public ResponseEntity<Processo> buscarPeloCodigo(@PathVariable Integer codigo, HttpServletResponse response) {
		Processo processo = processoService.buscarProcessoPeloCodigo(codigo);

		if (processo == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(processo);
	}

	@CrossOrigin
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Integer codigo) {
		processoService.deletar(codigo);
	}
	
	@CrossOrigin
	public List<Processo> listarTodos() {	
		return processoService.listarTodos();
	}
	
	@CrossOrigin
	@GetMapping("/pendenteaprovacao")
	public List<Processo> listarPendenteAprovacao(@RequestParam("usuarioId") Integer usuarioId) {	
		return processoService.listarPendenteAprovacao(usuarioId);
	}

}