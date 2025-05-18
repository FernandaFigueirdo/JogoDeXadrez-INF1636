package model;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class TorreTest {
    private Tabuleiro tabuleiro;
    private Torre torre;

    @Before
    public void setUp() {
        tabuleiro = new Tabuleiro();
        torre = new Torre(Cor.BRANCO, 4, 4);
        tabuleiro.colocarPeca(torre, 4, 4);
    }

    @Test
    public void testMovimentoInvalido() {
        List<Posicao> movimentos = torre.getMovimentosPossiveis(tabuleiro);

        Posicao posicaoInvalida = new Posicao(5, 5); 
        assertFalse("Torre não pode andar na diagonal", movimentos.contains(posicaoInvalida));
    }

    @Test 
    public void testMovimentoValido() {
        List<Posicao> movimentos = torre.getMovimentosPossiveis(tabuleiro);

        Posicao posicaoValida = new Posicao(5, 4); 
        assertTrue("Movimento correto! Torre pode andar para trás", movimentos.contains(posicaoValida));
    }

}
