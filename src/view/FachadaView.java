package view;

import model.JogoAPI;
import observer.Observador;
import java.awt.Frame;
import java.io.*;
import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;


public class FachadaView implements Observador {
	
	private final JogoAPI api = JogoAPI.getInstancia();
    private TabuleiroCanvas canvas;
    private Frame frame;

    public FachadaView(File arquivoCarregado) {
    	
        this.canvas = new TabuleiroCanvas();
        this.frame = new Frame("Jogo de Xadrez");
        
        frame.add(canvas);
        frame.setSize(652, 675);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent windowEvent){
                System.exit(0);
            }
        });

        api.registrarObservador(this);
        if (arquivoCarregado != null) {
        	controller.Controller.carregarPartidaDeArquivo(arquivoCarregado);
        }

    }
    
    public FachadaView() {
        this(null); 
    }

    @Override
    public void atualizar() {
    	System.out.println("View foi notificada pelo modelo.");
        canvas.repaint();
        if (JogoAPI.getInstancia().temPromocaoPendente()) {
            mostrarMenuPromocao();
        }
    }
    
    public void salvarEstado() {
    	controller.Controller.salvarPartida();
    }

    public void carregarEstado() {
    	controller.Controller.carregarPartida();
    }
    
    private void mostrarMenuPromocao() {
        JPopupMenu menu = new JPopupMenu();
        String[] opcoes = {"Rainha", "Torre", "Bispo", "Cavalo"};

        for (String tipo : opcoes) {
            JMenuItem item = new JMenuItem(tipo);
            item.addActionListener(e -> JogoAPI.getInstancia().aplicarPromocao(tipo.toUpperCase()));
            menu.add(item);
        }

        int linha = JogoAPI.getInstancia().getLinhaPromocao();
        int coluna = JogoAPI.getInstancia().getColunaPromocao();

        int x = coluna * 80;
        int y = linha * 80;

        menu.show(canvas, x, y);
    }


}
