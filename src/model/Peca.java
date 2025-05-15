package model;

import java.util.List;

abstract class Peca{
	private Cor cor;
	private int linha;
	private int coluna;
	
	public Peca(Cor cor, int posLinha, int posColuna) {
		this.cor=cor;
		this.linha=posLinha;
		this.coluna=posColuna;
	}
	
	public Cor getCor() {
		return cor;
	}
	
	public int getLinha() {
		return linha;
	}
	
	public int getColuna() {
		return coluna;
	}
	
	public void setPosicao(int posLinha,int posColuna) {
		this.linha=posLinha;
		this.coluna=posColuna;
	}
	
	public boolean mesmaCor(Peca peca2) {
		return this.cor == peca2.cor;
	}
	
	public boolean outraCor(Peca peca2) {
		return peca2 != null && this.cor != peca2.cor;
	}
	
	public abstract List<Posicao>getMovimentosPossiveis(Tabuleiro tabuleiro);
	
	@Override
    public String toString() {
        return "?"; 
	}
}