package model;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class JogoTest {

    private JogoAPI jogo;
    private Tabuleiro tabuleiro;

    /**
     * Inicializa uma instância do jogo e limpa o tabuleiro
     * antes de cada teste, para garantir um ambiente controlado.
     */
    @Before
    public void setUp() {
        jogo = JogoAPI.getInstancia();
        tabuleiro = jogo.getTabuleiro();

        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                tabuleiro.removePeca(i, j);
    }

    /**
     * Testa a detecção de um xeque comum.
     * Posiciona o rei branco na posição (4,4) e uma torre preta na mesma coluna (0,4).
     * A torre tem linha de ataque direta, então o rei branco deve estar em xeque.
     */
    @Test
    public void testXequeNormal() {
        Rei reiBranco = new Rei(Cor.BRANCO, 4, 4);
        tabuleiro.colocaPeca(reiBranco, 4, 4);

        Torre torrePreta = new Torre(Cor.PRETO, 0, 4);
        tabuleiro.colocaPeca(torrePreta, 0, 4);

        assertTrue("O rei branco está em xeque", jogo.estaEmXeque(Cor.BRANCO));
    }
    
    /**
     * Testa a detecção de um xeque descoberto.
     * Inicialmente o rei branco está protegido por um peão aliado,
     * bloqueando a linha de ataque de uma torre preta.
     * Ao remover o peão, o rei passa a estar em xeque.
     */
    @Test
    public void testXequeDescoberto() {
        Rei reiBranco = new Rei(Cor.BRANCO, 4, 4);
        tabuleiro.colocaPeca(reiBranco, 4, 4);

        Torre torrePreta = new Torre(Cor.PRETO, 0, 4);
        tabuleiro.colocaPeca(torrePreta, 0, 4);

        Peao peaoBranco = new Peao(Cor.BRANCO, 2, 4);
        tabuleiro.colocaPeca(peaoBranco, 2, 4);

        assertFalse("Ainda não está em xeque", jogo.estaEmXeque(Cor.BRANCO));

        tabuleiro.removePeca(2, 4);

        assertTrue("Agora está em xeque descoberto", jogo.estaEmXeque(Cor.BRANCO));
    }

}

