package view;

import model.JogoAPI;
import observer.Observador;
import java.awt.Frame;
import java.io.*;


public class FachadaView implements Observador {
	
	private final JogoAPI api = JogoAPI.getInstancia();
    private TabuleiroCanvas canvas;
    private Frame frame;

    public FachadaView(File arquivoCarregado) {
    	
        this.canvas = new TabuleiroCanvas();
        this.frame = new Frame("Jogo de Xadrez");
        
        if (arquivoCarregado != null) {
            JogoAPI.getInstancia().carregarEstadoDeArquivo(arquivoCarregado);
        }

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
    }
    
    public FachadaView() {
        this(null); 
    }

    @Override
    public void atualizar() {
    	System.out.println("View foi notificada pelo modelo.");
        canvas.repaint();
    }
    
    public void salvarEstado() {
        api.salvarEstadoComJFileChooser();
    }

    public void carregarEstado() {
        api.carregarEstadoComJFileChooser();
    }

}
