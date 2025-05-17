import static org.junit.Assert.*;
import org.junit.Test;

public class PecaTest {

    @Test(timeout = 2000)
    public void deveRetornarCorDaPeca() {
        Peca peca = new Peao(Cor.BRANCO);
        assertEquals("Era para retornar a cor correta da peça", Cor.BRANCO, peca.getCor());
    }
}
