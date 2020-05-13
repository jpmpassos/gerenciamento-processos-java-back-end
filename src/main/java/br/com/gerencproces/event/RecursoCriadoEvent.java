package br.com.gerencproces.event;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEvent;

public class RecursoCriadoEvent extends ApplicationEvent {
	private static final long serialVersionUID = 1L;
	private HttpServletResponse httpServletResponse;
	private Integer codigo;

	public RecursoCriadoEvent(Object source, HttpServletResponse httpServletResponse, Integer codigo) {
		super(source);
		this.codigo = codigo;
		this.httpServletResponse = httpServletResponse;
	}

	public HttpServletResponse getHttpServletResponse() {
		return httpServletResponse;
	}

	public Integer getCodigo() {
		return codigo;
	}

}
