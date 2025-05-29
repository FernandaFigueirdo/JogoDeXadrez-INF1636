package model;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class RainhaTest {
    private Tabuleiro tabuleiro;
    private Rainha rainha;

    /**
     * Posiciona uma rainha branca no centro do tabuleiro (linha 4, coluna 4).
     * Isso permite testar seus movimentos a partir de uma posição livre.
     */
    @Before
    public void setUp() {
        tabuleiro = new Tabuleiro();
        rainha = new Rainha(Cor.BRANCO, 4, 4);
        tabuleiro.colocaPeca(rainha, 4, 4);
    }

    
    /**
     * Testa um movimento inválido para a rainha.
     * A posição (5,6) representa um movimento em L, que não é permitido à rainha
     * (esse é um movimento típico do cavalo). Portanto, deve retornar false.
     */
    @Test
    public void testMovimentoInvalido() {
        List<Posicao> movimentos = rainha.getMovimentosPossiveis(tabuleiro);

        Posicao posicaoInvalida = new Posicao(5, 6); 
        assertFalse("Rainha não pode andar em L", movimentos.contains(posicaoInvalida));
    }

    /**
     * Testa movimentos válido da rainha.
     * Espera-se que a posição (456),(6, 4),(6, 6),
	 * esteja entre os movimentos possíveis.
     */
    @Test 
    public void testMovimentoValido() {
        List<Posicao> movimentos = rainha.getMovimentosPossiveis(tabuleiro);

        assertTrue("Rainha pode andar na horizontal", movimentos.contains(new Posicao(4,5)));
        
        assertTrue("Rainha pode andar na vertical)", movimentos.contains(new Posicao(6, 4)));
        
        assertTrue("Rainha pode andar na diagonal", movimentos.contains(new Posicao(6, 6)));
    }

}
