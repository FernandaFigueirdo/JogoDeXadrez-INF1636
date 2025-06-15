package view;

import model.JogoAPI;
import observer.Observador;

import java.awt.Frame;

public class FachadaView implements Observador {

    private TabuleiroCanvas canvas;
    private Frame frame;

    public FachadaView() {
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

        JogoAPI.getInstancia().registrarObservador(this);
    }

    @Override
    public void atualizar() {
    	System.out.println("[DEBUG] View foi notificada pelo modelo.");
        canvas.repaint();
    }
}
