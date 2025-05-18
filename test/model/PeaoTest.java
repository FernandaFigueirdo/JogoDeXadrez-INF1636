package model;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class PeaoTest {
    private Tabuleiro tabuleiro;
    private Peao peao;

    @Before
    public void setUp() {
        tabuleiro = new Tabuleiro();
        peao = new Peao(Cor.BRANCO, 4, 4);
        tabuleiro.colocarPeca(peao, 4, 4);
    }

    @Test
    public void testMovimentoInvalido() {
        List<Posicao> movimentos = peao.getMovimentosPossiveis(tabuleiro);

        Posicao posicaoInvalida = new Posicao(5, 4); 
        assertFalse("Peão não pode andar para trás", movimentos.contains(posicaoInvalida));
    }

    @Test 
    public void testMovimentoValido() {
        List<Posicao> movimentos = peao.getMovimentosPossiveis(tabuleiro);

        Posicao posicaoValida = new Posicao(3, 4); 
        assertTrue("Movimento correto! Peão pode andar para frente", movimentos.contains(posicaoValida));
    }

}
