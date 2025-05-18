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
	 * antes de cada teste.
	 */
	@Before
	public void setUp() {
		tabuleiro = new Tabuleiro();
		bispo = new Bispo(Cor.BRANCO, 4, 4);
		tabuleiro.colocarPeca(bispo, 4, 4);
	}

	/**
	 * Testa se o bispo não possui movimento horizontal (o que é inválido para essa peça).
	 * Espera-se que a posição (4,6) ,duas colunas à direita e mesma linha,
	 * não esteja entre os movimentos possíveis.
	 */
	@Test
	public void testMovimentoInvalido() {
		List<Posicao> movimentos = bispo.getMovimentosPossiveis(tabuleiro);

		Posicao posicaoInvalida = new Posicao(4, 6); 
		assertFalse("Bispo não pode andar na horizontal", movimentos.contains(posicaoInvalida));
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
