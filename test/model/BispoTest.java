import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import model.*;
import java.util.List;

public class BispoTest {

    private Tabuleiro tabuleiro;

    @Before
    public void setUp() {
        tabuleiro = new Tabuleiro();
    }

    @Test(timeout = 2000)
    public void deveMoverPraDiagonalCimaDireita() {
        Bispo bispo = new Bispo(Cor.BRANCO, 3, 3);
        tabuleiro.colocarPeca(bispo, 3, 3);

        List<Posicao> movimentos = bispo.getMovimentosPossiveis(tabuleiro);

        assertTrue("Era pra poder ir pra (2,4)", movimentos.contains(new Posicao(2, 4)));
    }

    @Test(timeout = 2000)
    public void deveMoverPraDiagonalBaixoEsquerda() {
        Bispo bispo = new Bispo(Cor.BRANCO, 3, 3);
        tabuleiro.colocarPeca(bispo, 3, 3);

        List<Posicao> movimentos = bispo.getMovimentosPossiveis(tabuleiro);

        assertTrue("Era pra poder ir pra (4,2)", movimentos.contains(new Posicao(4, 2)));
    }

    @Test(timeout = 2000)
    public void naoDevePassarSeTiverPecaMesmaCor() {
        Bispo bispo = new Bispo(Cor.BRANCO, 3, 3);
        tabuleiro.colocarPeca(bispo, 3, 3);

        Peao bloqueio = new Peao(Cor.BRANCO, 2, 4);
        tabuleiro.colocarPeca(bloqueio, 2, 4);

        List<Posicao> movimentos = bispo.getMovimentosPossiveis(tabuleiro);

        assertFalse("Não deveria conseguir passar da peça em (2,4)", movimentos.contains(new Posicao(2, 4)));
    }

    @Test(timeout = 2000)
    public void deveConseguirCapturarPecaDoInimigo() {
        Bispo bispo = new Bispo(Cor.BRANCO, 3, 3);
        tabuleiro.colocarPeca(bispo, 3, 3);

        Peao adversario = new Peao(Cor.PRETO, 2, 4);
        tabuleiro.colocarPeca(adversario, 2, 4);

        List<Posicao> movimentos = bispo.getMovimentosPossiveis(tabuleiro);

        assertTrue("Deveria conseguir capturar a peça do adversário em (2,4)", movimentos.contains(new Posicao(2, 4)));
    }
}
