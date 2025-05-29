package model;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class TorreTest {
    private Tabuleiro tabuleiro;
    private Torre torre;

    /**
     * Posiciona a torre branca na casa central (4,4) antes de cada teste.
     * Isso permite testar seus movimentos a partir de uma posição livre.
     */
    @Before
    public void setUp() {
        tabuleiro = new Tabuleiro();
        torre = new Torre(Cor.BRANCO, 4, 4);
        tabuleiro.colocaPeca(torre, 4, 4);
    }

    /**
     * Testa um movimento inválido da torre.
     * A posição (5,5),(5,6) representa movimentações não válidas,
     * já que a torre se move apenas na vertical ou horizontal.
     */
    @Test
    public void testMovimentoInvalido() {
        List<Posicao> movimentos = torre.getMovimentosPossiveis(tabuleiro);

        assertFalse("Torre não pode andar na diagonal", movimentos.contains(new Posicao(5, 5)));
        
        assertFalse("Torre não pode andar em L", movimentos.contains(new Posicao(5, 6)));
    }

    /**
     * Testa um movimento válido da torre.
     * A posição (3,4), (4,5), representa movimentos
     * que estão dentro das regras de movimentação da torre.
     */
    @Test 
    public void testMovimentoValido() {
        List<Posicao> movimentos = torre.getMovimentosPossiveis(tabuleiro);

        assertTrue("Movimento correto: Torre pode andar na vertical", movimentos.contains(new Posicao(3, 4)));

        assertTrue("Movimento correto: Torre pode andar para a horizontal", movimentos.contains(new Posicao(4, 5)));
    }

}
