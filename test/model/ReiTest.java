package model;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class ReiTest {
    private Tabuleiro tabuleiro;
    private Rei rei;

    /**
     * Posiciona o rei branco na casa central (4,4) antes de cada teste.
     * Isso permite testar seus movimentos a partir de uma posição livre.
     */
    @Before
    public void setUp() {
        tabuleiro = new Tabuleiro();
        rei = new Rei(Cor.BRANCO, 4, 4);
        tabuleiro.colocaPeca(rei, 4, 4);
    }

    /**
     * Testa um movimento inválido para o rei.
     * As movimentações para (2,4),(4,2),(2,2) são inválidas 
     * já que o rei só pode se mover uma casa por vez em qualquer direção.
     */
    @Test
    public void testMovimentoInvalido() {
        List<Posicao> movimentos = rei.getMovimentosPossiveis(tabuleiro);

        assertFalse("Rei não pode andar duas casas na vertical", movimentos.contains(new Posicao(2, 4)));

        assertFalse("Rei não pode andar duas casas na horizontal", movimentos.contains(new Posicao(4, 2)));

        assertFalse("Rei não pode andar duas casas na diagonal", movimentos.contains(new Posicao(2, 2)));

   }


    /**
     * Testa um movimento válido do rei.
     * De (4,4) para (4,3),(3,4),(3,3)
     * está dentro das regras permitidas para o rei.
     */
    @Test 
    public void testMovimentoValido() {
        List<Posicao> movimentos = rei.getMovimentosPossiveis(tabuleiro);
        
        assertTrue("Movimento correto! Rei pode andar na vertical", movimentos.contains(new Posicao(3, 4)));

        assertTrue("Movimento correto! Rei pode andar na horizontal", movimentos.contains(new Posicao(4, 3)));

        assertTrue("Movimento correto! Rei pode andar na diagonal", movimentos.contains(new Posicao(3, 3)));

    }

}
