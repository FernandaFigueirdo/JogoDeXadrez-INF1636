package model;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class BispoTest {
	private Tabuleiro tabuleiro;
	private Bispo bispo;

	@Before
	public void setUp() {
		tabuleiro = new Tabuleiro();
		bispo = new Bispo(Cor.BRANCO, 4, 4);
		tabuleiro.colocarPeca(bispo, 4, 4);
	}

	@Test
	public void testMovimentoInvalido() {
		List<Posicao> movimentos = bispo.getMovimentosPossiveis(tabuleiro);

		Posicao posicaoInvalida = new Posicao(4, 6); 
		assertFalse("Bispo não pode andar na horizontal", movimentos.contains(posicaoInvalida));
	}
	
	@Test 
	public void testMovimentoValido() {
		List<Posicao> movimentos = bispo.getMovimentosPossiveis(tabuleiro);

		Posicao posicaoValida = new Posicao(6, 6); 
		assertTrue("Movimento correto! Bispo pode andar na diagonal", movimentos.contains(posicaoValida));
	}
	
}
