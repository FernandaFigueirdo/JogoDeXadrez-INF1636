package model;

import java.util.List;

//Classe abstrata que representa uma peça genérica do xadrez
abstract class Peca{
	private Cor cor; // Cor da peça (BRANCO ou PRETO)
	private int linha; // Linha atual da peça no tabuleiro
	private int coluna; // Coluna atual da peça no tabuleiro

	// Construtor: define a cor e a posição inicial da peça
	public Peca(Cor cor, int posLinha, int posColuna) {
		this.cor=cor;
		this.linha=posLinha;
		this.coluna=posColuna;
	}
	
	// Retorna a cor da peça
	public Cor getCor() {
		return cor;
	}
	
	// Retorna a linha atual da peça
	public int getLinha() {
		return linha;
	}
	
	 // Retorna a coluna atual da peça
	public int getColuna() {
		return coluna;
	}
	
	protected boolean movido = false;
	
	public boolean jaMovimentou() {
		return movido;
	}
	
	public void moveu() {
		this.movido = true;
	}
	
	// Atualiza a posição da peça
	public void setPosicao(int posLinha,int posColuna) {
		this.linha=posLinha;
		this.coluna=posColuna;
	}
	
	// Verifica se outra peça tem a mesma cor
	public boolean mesmaCor(Peca peca2) {
		return this.cor == peca2.cor;
	}
	
	// Verifica se outra peça é de cor diferente
	public boolean outraCor(Peca peca2) {
		return peca2 != null && this.cor != peca2.cor;
	}
	
	// Método abstrato: cada peça concreta deve implementar os movimentos possíveis
	public abstract List<Posicao>getMovimentosPossiveis(Tabuleiro tabuleiro);
	
	// Método abstrato: cada peça deve retornar seu tipo como string 
	public abstract String getTipo();
	
	 // Representação textual padrão
	@Override
    public String toString() {
        return "?"; 
	}
}