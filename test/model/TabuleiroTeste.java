import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TabuleiroTest {

    private Tabuleiro tabuleiro;

    @Before
    public void setUp() {
        tabuleiro = new Tabuleiro();
    }

    @Test(timeout = 2000)
    public void deveRetornarNuloParaPosicaoVazia() {
        assertNull("Esperava nulo para posição vazia", tabuleiro.getPeca(4, 4));
    }

    @Test(timeout = 2000)
    public void deveColocarEPegarPecaCorretamente() {
        Peca peca = new Peao(Cor.BRANCO);
        tabuleiro.setPeca(2, 2, peca);
        assertEquals("Deveria recuperar a peça colocada", peca, tabuleiro.getPeca(2, 2));
    }
}
