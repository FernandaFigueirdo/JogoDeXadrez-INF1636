package model;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class BispoTest {
	private Tabuleiro tabuleiro;
	private Bispo bispo;
	
	
	/**
	 * Inicializa o tabuleiro e posiciona um bispo branco na casa central (4,4),
	 * Isso permite testar seus movimentos a partir de uma posição livre.
	 */
	@Before
	public void setUp() {
		tabuleiro = new Tabuleiro();
		bispo = new Bispo(Cor.BRANCO, 4, 4);
		tabuleiro.colocaPeca(bispo, 4, 4);
	}

	/**
	 * Testa se o bispo não possui movimento horizontal, vertical e em L (o que é inválido para essa peça).
	 * Espera-se que a posição (4,6),(6, 4),(6, 5),
	 * não esteja entre os movimentos possíveis.
	 */
	@Test
	public void testMovimentoInvalido() {
		List<Posicao> movimentos = bispo.getMovimentosPossiveis(tabuleiro);
		
	    assertFalse("Bispo não pode andar na horizontal", movimentos.contains(new Posicao(4, 6)));

	    assertFalse("Bispo não pode andar na vertical", movimentos.contains(new Posicao(6, 4)));

	    assertFalse("Bispo não pode andar em L como o cavalo", movimentos.contains(new Posicao(6, 5)));
	}
	
	/**
	 * Testa se o bispo possui movimento diagonal válido.
	 * A posição (6,6) está duas casas na diagonal inferior-direita
	 * e deve estar presente na lista de movimentos possíveis.
	 */
	@Test 
	public void testMovimentoValido() {
		List<Posicao> movimentos = bispo.getMovimentosPossiveis(tabuleiro);

		Posicao posicaoValida = new Posicao(6, 6); 
		assertTrue("Movimento correto! Bispo pode andar na diagonal", movimentos.contains(posicaoValida));
	}
	
}
