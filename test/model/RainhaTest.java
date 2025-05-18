package model;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class RainhaTest {
    private Tabuleiro tabuleiro;
    private Rainha rainha;

    @Before
    public void setUp() {
        tabuleiro = new Tabuleiro();
        rainha = new Rainha(Cor.BRANCO, 4, 4);
        tabuleiro.colocarPeca(rainha, 4, 4);
    }

    @Test
    public void testMovimentoInvalido() {
        List<Posicao> movimentos = rainha.getMovimentosPossiveis(tabuleiro);

        Posicao posicaoInvalida = new Posicao(5, 6); 
        assertFalse("Rainha não pode andar em L", movimentos.contains(posicaoInvalida));
    }

    @Test 
    public void testMovimentoValido() {
        List<Posicao> movimentos = rainha.getMovimentosPossiveis(tabuleiro);

        Posicao posicaoValida = new Posicao(5, 4); 
        assertTrue("Movimento correto! Rainha pode andar para trás", movimentos.contains(posicaoValida));
    }

}
