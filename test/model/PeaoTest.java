import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import model.*;
import java.util.List;

public class PeaoTest {

    private Tabuleiro tabuleiro;

    @Before
    public void setUp() {
        tabuleiro = new Tabuleiro();
    }

    @Test(timeout = 2000)
    public void deveAndarUmaCasaPraFrente() {
        Peao peao = new Peao(Cor.BRANCO, 5, 4);
        tabuleiro.colocarPeca(peao, 5, 4);

        List<Posicao> movimentos = peao.getMovimentosPossiveis(tabuleiro);

        assertTrue("Era para poder andar uma casa pra frente", movimentos.contains(new Posicao(4, 4)));
    }

    @Test(timeout = 2000)
    public void deveAndarDuasCasasSeEstiverNaLinhaInicial() {
        Peao peao = new Peao(Cor.BRANCO, 6, 3);
        tabuleiro.colocarPeca(peao, 6, 3);

        List<Posicao> movimentos = peao.getMovimentosPossiveis(tabuleiro);

        assertTrue("Deveria poder andar duas casas pra frente", movimentos.contains(new Posicao(4, 3)));
    }

    @Test(timeout = 2000)
    public void naoDeveAndarSeTiverPecaNaFrente() {
        Peao peao = new Peao(Cor.BRANCO, 5, 2);
        Peao bloqueio = new Peao(Cor.PRETO, 4, 2);
        tabuleiro.colocarPeca(peao, 5, 2);
        tabuleiro.colocarPeca(bloqueio, 4, 2);

        List<Posicao> movimentos = peao.getMovimentosPossiveis(tabuleiro);

        assertFalse("Não era para poder andar se tiver peça na frente", movimentos.contains(new Posicao(4, 2)));
    }

    @Test(timeout = 2000)
    public void deveCapturarNaDiagonal() {
        Peao peao = new Peao(Cor.BRANCO, 5, 5);
        Peao inimigo = new Peao(Cor.PRETO, 4, 4);
        tabuleiro.colocarPeca(peao, 5, 5);
        tabuleiro.colocarPeca(inimigo, 4, 4);

        List<Posicao> movimentos = peao.getMovimentosPossiveis(tabuleiro);

        assertTrue("Deveria ser capaz de capturar peça inimiga na diagonal", movimentos.contains(new Posicao(4, 4)));
    }

    @Test(timeout = 2000)
    public void naoPodeCapturarSeForMesmaCor() {
        Peao peao = new Peao(Cor.BRANCO, 5, 5);
        Peao aliado = new Peao(Cor.BRANCO, 4, 6);
        tabuleiro.colocarPeca(peao, 5, 5);
        tabuleiro.colocarPeca(aliado, 4, 6);

        List<Posicao> movimentos = peao.getMovimentosPossiveis(tabuleiro);

        assertFalse("Não deveria ser possível capturar peça da mesma cor", movimentos.contains(new Posicao(4, 6)));
    }
}
