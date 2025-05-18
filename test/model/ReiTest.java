package model;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class ReiTest {
    private Tabuleiro tabuleiro;
    private Rei rei;

    @Before
    public void setUp() {
        tabuleiro = new Tabuleiro();
        rei = new Rei(Cor.BRANCO, 4, 4);
        tabuleiro.colocarPeca(rei, 4, 4);
    }

    @Test
    public void testMovimentoInvalido() {
        List<Posicao> movimentos = rei.getMovimentosPossiveis(tabuleiro);

        Posicao posicaoInvalida = new Posicao(6, 4); 
        assertFalse("Rei não pode andar 2 casas", movimentos.contains(posicaoInvalida));
    }

    @Test 
    public void testMovimentoValido() {
        List<Posicao> movimentos = rei.getMovimentosPossiveis(tabuleiro);

        Posicao posicaoValida = new Posicao(5, 4); 
        assertTrue("Movimento correto! Rei pode andar para trás", movimentos.contains(posicaoValida));
    }

}
