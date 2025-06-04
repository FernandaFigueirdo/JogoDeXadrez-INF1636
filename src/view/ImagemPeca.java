package view;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

import model.JogoAPI;

public class ImagemPeca {
    private static final Map<String, BufferedImage> imagens = new HashMap<>();

    static {
        try {
            imagens.put("PEAO_BRANCO", carregar("/imagens/CyanP.png"));
            imagens.put("PEAO_PRETO", carregar("/imagens/PurpleP.png"));
            imagens.put("BISPO_BRANCO", carregar("/imagens/CyanB.png"));
            imagens.put("BISPO_PRETO", carregar("/imagens/PurpleB.png"));
            imagens.put("REI_BRANCO", carregar("/imagens/CyanK.png"));
            imagens.put("REI_PRETO", carregar("/imagens/PurpleK.png"));
            imagens.put("RAINHA_BRANCO", carregar("/imagens/CyanQ.png"));
            imagens.put("RAINHA_PRETO", carregar("/imagens/PurpleQ.png"));
            imagens.put("CAVALO_BRANCO", carregar("/imagens/CyanN.png"));
            imagens.put("CAVALO_PRETO", carregar("/imagens/PurpleN.png"));
            imagens.put("TORRE_BRANCO", carregar("/imagens/CyanR.png"));
            imagens.put("TORRE_PRETO", carregar("/imagens/PurpleR.png"));
        } catch (IOException e) {
            System.err.println("Problema ao carregar imagens das peças: " + e.getMessage());
        }
    }

    private static BufferedImage carregar(String caminho) throws IOException {
        return ImageIO.read(ImagemPeca.class.getResource(caminho));
    }

    public static BufferedImage getImagem(int linha, int coluna) {
        JogoAPI jogo = JogoAPI.getInstancia();
        String tipo = jogo.getTipoPeca(linha, coluna);
        String cor = jogo.getCorPeca(linha, coluna);

        if (tipo == null || cor == null) return null;

        String chave = tipo + "_" + cor;
        return imagens.get(chave);
    }
}
