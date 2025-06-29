package view;

import java.awt.Canvas;
import controller.MenuInicial;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;


import model.JogoAPI;
import model.MovimentoDTO;
import model.ResultadoJogo;
import view.ImagemPeca;

public class TabuleiroCanvas extends Canvas implements MouseListener {
    private static final int TAM_CASA = 80;
    private final JogoAPI jogo;
    private List<MovimentoDTO> movimentosPossiveis;
    

    public TabuleiroCanvas() {
        this.jogo = JogoAPI.getInstancia();
        this.addMouseListener(this);
        this.setSize(640, 640);
    }

    @Override
    public void paint(Graphics g) {
    	BufferedImage offscreen = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
    	Graphics2D g2 = offscreen.createGraphics();
        desenharTabuleiro(g2);
        desenharPecas(g2);
        destacarMovimentos(g2);
        
        g.drawImage(offscreen, 0, 0, null);
        g2.dispose();
    }

    private void desenharTabuleiro(Graphics2D g2) {
        for (int linha = 0; linha < 8; linha++) {
            for (int coluna = 0; coluna < 8; coluna++) {
                if ((linha + coluna) % 2 == 0) {
                    g2.setColor(new Color(240, 240, 240));
                } else {
                    g2.setColor(new Color(30, 30, 30));
                }
                g2.fillRect(coluna * TAM_CASA, linha * TAM_CASA, TAM_CASA, TAM_CASA);
            }
        }
    }

    private void desenharPecas(Graphics2D g2) {
        for (int linha = 0; linha < 8; linha++) {
            for (int coluna = 0; coluna < 8; coluna++) {
                BufferedImage imagem = ImagemPeca.getImagem(linha, coluna);
                if (imagem != null) {
                    g2.drawImage(imagem, coluna * TAM_CASA, linha * TAM_CASA, TAM_CASA, TAM_CASA, null);
                }
            }
        }
    }

    private void destacarMovimentos(Graphics2D g2) {
        if (movimentosPossiveis != null) {
            g2.setColor(new Color(255, 0, 0, 128));
            for (MovimentoDTO pos : movimentosPossiveis) {
                g2.fillRect(pos.coluna * TAM_CASA, pos.linha * TAM_CASA, TAM_CASA, TAM_CASA);
            }
        }
    }
    

    @Override
    public void mouseClicked(MouseEvent e) {
        int coluna = e.getX() / TAM_CASA;
        int linha = e.getY() / TAM_CASA;

        if (movimentosPossiveis == null) {
            boolean sucesso = jogo.selecionaPeca(linha, coluna);
            if (sucesso) {
                movimentosPossiveis = jogo.getMovimentosPossiveisSelecionada();
            }
        } else {
        	boolean moveu = jogo.selecionaCasa(linha, coluna);
            movimentosPossiveis = null;
            if (moveu) {
                ResultadoJogo status = jogo.verificarFimDeJogo(); 
                if (status != ResultadoJogo.EM_ANDAMENTO) {
                    String msg = switch (status) {
                        case XEQUE_MATE_BRANCO -> "Xeque-mate! Vitória das peças BRANCAS!";
                        case XEQUE_MATE_PRETO -> "Xeque-mate! Vitória das peças PRETAS!";
                        case CONGELAMENTO -> "Empate por congelamento!";
                        default -> "";
                    };
                    JOptionPane.showMessageDialog(this, msg);

                    ((java.awt.Window) this.getParent().getParent()).dispose();
                    controller.MenuInicial.mostrarMenu();
                }
            }
        }
        if (e.getButton() == MouseEvent.BUTTON3) {
            JPopupMenu popup = new JPopupMenu();

            JMenuItem salvarItem = new JMenuItem("Salvar Jogo");
            salvarItem.addActionListener(ev -> controller.Controller.salvarPartida()); 

            JMenuItem encerrarItem = new JMenuItem("Encerrar Jogo");
            encerrarItem.addActionListener(ev -> {
            	JOptionPane.showMessageDialog(this, "Partida encerrada.");
            	((java.awt.Window) this.getParent()).dispose(); // Fecha a janela do tabuleiro
            	controller.MenuInicial.mostrarMenu(); // Mostra o menu inicial novamente

            });

            popup.add(salvarItem);
            popup.add(encerrarItem);
            popup.show(this, e.getX(), e.getY());
            return;
        }


        repaint();
    }
    
    @Override
    public void update(Graphics g) {
        paint(g); 
    }


    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {} 
    

}
