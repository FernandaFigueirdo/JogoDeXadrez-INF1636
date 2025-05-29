package model;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class CavaloTest {
    private Tabuleiro tabuleiro;
    private Cavalo cavalo;

    /**
     * Posiciona um cavalo branco na casa central (4,4) antes de cada teste,
     * Isso permite testar seus movimentos a partir de uma posição livre.
     */
    @Before
    public void setUp() {
        tabuleiro = new Tabuleiro();
        cavalo = new Cavalo(Cor.BRANCO, 4, 4);
        tabuleiro.colocaPeca(cavalo, 4, 4);
    }

    /**
     * Testa se o cavalo não pode se mover em uma direção inválida.
     * O cavalo se movimenta em L então a posição (5,5),(3, 4),(4, 3),
     * não deve estar presente na lista de movimentos possíveis.
     */
    @Test
    public void testMovimentoInvalido() {
        List<Posicao> movimentos = cavalo.getMovimentosPossiveis(tabuleiro);

        assertFalse("Cavalo não pode andar na diagonal)", movimentos.contains(new Posicao(5, 5)));
        
        assertFalse("Cavalo não pode andar apenas na vertical)", movimentos.contains(new Posicao(3, 4)));
        
        assertFalse("Cavalo não pode andar apenas na horizontal)", movimentos.contains(new Posicao(4, 3)));

    }

    /**
     * Testa se o cavalo pode executar um movimento válido no formato de L.
     * A posição (6,5) está duas casas abaixo e uma casa à direita da posição inicial (4,4),
     * representando um dos movimentos clássicos do cavalo.
     */
    @Test 
    public void testMovimentoValido() {
        List<Posicao> movimentos = cavalo.getMovimentosPossiveis(tabuleiro);

        Posicao posicaoValida = new Posicao(6, 5); 
        assertTrue("Movimento correto! Cavalo pode andar em L", movimentos.contains(posicaoValida));
    }

}
