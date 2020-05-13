package br.com.gerencproces.repository.query;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.gerencproces.model.Processo;

public class ProcessoRepositoryQueryImpl implements ProcessoRepositoryQuery {

	@Autowired
	private EntityManagerFactory entityManagerFactory;


	@Override
	public List<Processo> listarProcessosPendentes(Integer usuarioId) {
		
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		StringBuffer buffer = new StringBuffer();
		try {
			entityManager.getTransaction().begin();

			buffer.append(" SELECT * ");
			buffer.append("   from processo pr ");
			buffer.append("   join parecer pa on ( ");
			buffer.append("          pr.processo_id = pa.processo_id and pa.status = 'PENDENTE' and pa.usuario_id = :usuarioId ");
			buffer.append("                      )");

			Query query = entityManager.createNativeQuery(buffer.toString(), Processo.class);
			query.setParameter("usuarioId", usuarioId);

			List<Processo> list = query.getResultList();

			entityManager.getTransaction().commit();
			return list;
		} finally {
			entityManager.close();
		}
	}

}
