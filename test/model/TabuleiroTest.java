import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import model.*;

public class TabuleiroTest {

    private Tabuleiro tabuleiro;

    @Before
    public void setUp() {
        tabuleiro = new Tabuleiro();
    }

    @Test(timeout = 2000)
    public void deveRetornarCasaNaoNula() {
        Casa casa = tabuleiro.getCasa(4, 4);
        assertNotNull("Era para retornar uma casa existente", casa);
    }

    @Test(timeout = 2000)
    public void deveRetornarNuloQuandoNaoHaPeca() {
        Peca peca = tabuleiro.getPeca(4, 4);
        assertNull("Era para ser nulo quando não há peça", peca);
    }

    @Test(timeout = 2000)
    public void deveColocarERecuperarPecaCorretamente() {
        Peao peao = new Peao(Cor.BRANCO);
        tabuleiro.colocarPeca(peao, 2, 2);
        Peca pecaRecuperada = tabuleiro.getPeca(2, 2);
        assertEquals("Era para recuperar a peça colocada", peao, pecaRecuperada);
    }

    @Test(timeout = 2000)
    public void deveRemoverPecaCorretamente() {
        Peao peao = new Peao(Cor.BRANCO);
        tabuleiro.colocarPeca(peao, 3, 3);
        tabuleiro.removerPeca(3, 3);
        assertNull("Era para a posição estar vazia após remover a peça", tabuleiro.getPeca(3, 3));
    }

    @Test(timeout = 2000)
    public void deveVerificarSePosicaoEstaDentroDosLimites() {
        assertTrue("Era para estar dentro do tabuleiro", tabuleiro.estaDentro(0, 0));
        assertFalse("Não era para estar dentro do tabuleiro", tabuleiro.estaDentro(-1, 0));
        assertFalse("Não era para estar dentro do tabuleiro", tabuleiro.estaDentro(8, 8));
    }
}
