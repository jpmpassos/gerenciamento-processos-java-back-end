package br.com.gerencproces;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.gerencproces.model.Usuario;
import br.com.gerencproces.repository.UsuarioRepository;
import br.com.gerencproces.service.UsuarioService;
import br.com.gerencproces.util.FuncoesUtils;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class GerencProcesApplicationTests {
	
	@Mock
	UsuarioRepository usuarioRepository;

	@InjectMocks
	private UsuarioService usuarioService;
	
	@Before
	public void before() {
		when(usuarioRepository.findAll()).thenReturn(Arrays.asList(
				new Usuario()			
			));
	}

	@Test
	public void testCaucularDistanciaDeCoodenadas() {
	
		double distanciaTeste = FuncoesUtils.caucularDistanciaDeCoodenadas(20, 10, 28, 2);

		assertEquals(distanciaTeste, 11.3137084989848, 0.0001);		
		
	}
	
	@Test
	public void testListarPorProximidadeJava() {
		
		/*List<Usuario> listTeste = usuarioService.listarPorProximidadeJava(20, 10, new BigDecimal(10));
		
		List<Usuario> listResult = Arrays.asList(
					new Usuario(1, "Lanchonete", 27, 12),
					new Usuario(3, "Joalheria", 15, 12),
					new Usuario(5, "Pub", 12, 8),
					new Usuario(6, "Supermercado", 23, 6)
				);
	
		assertArrayEquals(listTeste.toArray(), listResult.toArray());	*/	
		
	}


}

