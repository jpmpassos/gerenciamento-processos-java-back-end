package br.com.gerencproces.service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.gerencproces.model.Parecer;
import br.com.gerencproces.model.Processo;
import br.com.gerencproces.model.Usuario;
import br.com.gerencproces.model.enums.StatusParecerEnum;
import br.com.gerencproces.repository.ParecerRepository;
import br.com.gerencproces.repository.ProcessoRepository;
import br.com.gerencproces.repository.UsuarioRepository;

@Service
public class ParecerService {

	@Autowired
	private ParecerRepository parecerRepository;
	@Autowired
	private ProcessoRepository processoRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;

	public Parecer salvar(Parecer parecer) throws NoSuchAlgorithmException {

		Usuario usuario = usuarioRepository.findById(parecer.getUsuario().getUsuarioId()).orElse(null);

		if (usuario == null) {
			throw new EmptyResultDataAccessException("Usuário não encontrado!", 0);
		}

		Processo processo = processoRepository.findById(parecer.getProcesso().getProcessoId()).orElse(null);

		if (processo == null) {
			throw new EmptyResultDataAccessException("Processo não encontrado!", 0);
		}

		parecer.setUsuario(usuario);

		parecer.setProcesso(processo);

		parecer.setStatus(StatusParecerEnum.PENDENTE);

		return parecerRepository.save(parecer);
	}

	public Parecer atualizar(Integer codigo, Parecer parecer) {
		Parecer parecerBanco = buscarParecerPeloCodigo(codigo);
		BeanUtils.copyProperties(parecer, parecerBanco, "parecerId", "usuario", "processo");
		return parecerRepository.save(parecerBanco);
	}

	public Parecer buscarParecerPeloCodigo(Integer codigo) {
		Parecer parecerBanco = parecerRepository.findById(codigo).orElse(null);
		if (parecerBanco == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return parecerBanco;
	}

	public List<Parecer> listarTodos() {
		return parecerRepository.findAll();
	}

	public List<Parecer> listarPorProcesso(Integer processoId) {
		return parecerRepository.listarPorProcesso(processoId);
	}
	
	public List<Parecer> listarPendentePorUsuario(Integer usuarioId) {
		return parecerRepository.listarPendentePorUsuario(usuarioId);
	}

	public void deletar(Integer codigo) {
		parecerRepository.deleteById(codigo);
	}

}
