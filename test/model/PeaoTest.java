import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

public class PeaoTest {

    private Tabuleiro tabuleiro;

    @Before
    public void setUp() {
        tabuleiro = new Tabuleiro();
    }

    @Test(timeout = 2000)
    public void deveMoverUmaCasaFrente() {
        Peao peao = new Peao(Cor.BRANCO);
        tabuleiro.setPeca(1, 0, peao);
        List<Posicao> movimentos = peao.getMovimentosPossiveis(tabuleiro, 1, 0);
        assertTrue("Era para ser possível mover uma casa para frente", movimentos.contains(new Posicao(2, 0)));
    }
}
