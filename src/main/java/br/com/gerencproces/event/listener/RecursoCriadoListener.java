package br.com.gerencproces.event.listener;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.gerencproces.event.RecursoCriadoEvent;

@Component
public class RecursoCriadoListener implements ApplicationListener<RecursoCriadoEvent> {

	@Override
	public void onApplicationEvent(RecursoCriadoEvent recursoCriadoEvent) {
		HttpServletResponse httpServletResponse = recursoCriadoEvent.getHttpServletResponse();
		Integer codigo = recursoCriadoEvent.getCodigo();

		adicionarHeaderLocation(httpServletResponse, codigo);
	}

	private void adicionarHeaderLocation(HttpServletResponse httpServletResponse, Integer codigo) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
				.buildAndExpand(codigo.toString()).toUri();
		httpServletResponse.setHeader("Location", uri.toASCIIString());
	}

}
