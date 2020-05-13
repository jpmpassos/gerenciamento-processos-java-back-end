package br.com.gerencproces.service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.gerencproces.model.Processo;
import br.com.gerencproces.repository.ProcessoRepository;

@Service
public class ProcessoService {

	@Autowired
	private ProcessoRepository processoRepository;

	public Processo salvar(Processo processo) throws NoSuchAlgorithmException {

		return processoRepository.save(processo);
	}

	public Processo atualizar(Integer codigo, Processo processo) {
		Processo processoBanco = buscarProcessoPeloCodigo(codigo);
		BeanUtils.copyProperties(processo, processoBanco, "processoId");
		return processoRepository.save(processoBanco);
	}

	public Processo buscarProcessoPeloCodigo(Integer codigo) {
		Processo processoBanco = processoRepository.findById(codigo).orElse(null);
		if (processoBanco == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return processoBanco;
	}

	public List<Processo> listarTodos() {
		return processoRepository.findAll();
	}

	public void deletar(Integer codigo) {
		processoRepository.deleteById(codigo);
	}
	
	public List<Processo> listarPendenteAprovacao(Integer usuarioId) {
		return processoRepository.listarProcessosPendentes(usuarioId);
	}

}
