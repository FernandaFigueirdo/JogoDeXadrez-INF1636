package model;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class PeaoTest {
    private Tabuleiro tabuleiro;
    private Peao peao;

    /**
     * Coloca um peão branco na posição (4,4) no tabuleiro antes de cada teste.
     * Isso permite testar seus movimentos a partir de uma posição livre.
     */
    @Before
    public void setUp() {
        tabuleiro = new Tabuleiro();
        peao = new Peao(Cor.BRANCO, 4, 4);
        tabuleiro.colocaPeca(peao, 4, 4);
    }

    /**
     * Testa se o peão branco não pode se mover em direções inválidas.
     * As posições (5,4),(4,3),(5,6)(6,6) são inválidas, já que o peão 
     * só pode ir para a frente
     */
    @Test
    public void testMovimentoInvalido() {
        List<Posicao> movimentos = peao.getMovimentosPossiveis(tabuleiro);

        assertFalse("Peão não pode andar para trás", movimentos.contains(new Posicao(5, 4)));
        
        assertFalse("Peão não pode andar na horizontal", movimentos.contains(new Posicao(4, 3)));
        
        assertFalse("Peão não pode andar em L", movimentos.contains(new Posicao(5, 6)));
        
        assertFalse("Peão não pode andar na diagonal", movimentos.contains(new Posicao(6, 6)));
    }

    /**
     * Testa se o peão branco pode realizar um movimento válido para frente.
     * De (4,4) para (3,4) é um avanço de uma casa para cima, que é permitido.
     */
    @Test 
    public void testMovimentoValido() {
        List<Posicao> movimentos = peao.getMovimentosPossiveis(tabuleiro);

        Posicao posicaoValida = new Posicao(3, 4); 
        assertTrue("Movimento correto! Peão pode andar para frente", movimentos.contains(posicaoValida));
    }

}
