package controller;

import model.JogoAPI;
import observer.Observador;

public class Controller {
    private final JogoAPI jogo;

    public Controller() {
        this.jogo = JogoAPI.getInstancia();
    }

    public void registrarObservador(Observador obs) {
        jogo.registrarObservador(obs);
    }

    public void selecionarPeca(int linha, int coluna) {
        jogo.selecionaPeca(linha, coluna);
    }

    public void selecionarCasa(int linha, int coluna) {
        jogo.selecionaCasa(linha, coluna);
    }

    public JogoAPI getJogo() {
        return jogo;
    }
}
