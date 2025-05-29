
package model;

//Classe que representa uma casa do tabuleiro de xadrez
class Casa {
	private final int linha; // linha da casa no tabuleiro (0 a 7)
	private final int coluna; // coluna da casa no tabuleiro (0 a 7)
	private Peca peca; // peça que está ocupando esta casa (pode ser null)
	
	// Construtor: define a linha e a coluna da casa e inicializa como vazia (peca = null)
	public Casa(int linha, int coluna) {
		this.linha= linha;
		this.coluna=coluna;
		this.peca=null;
	}
	
	// Retorna a linha da casa
	public int getLinha() {
		return linha;
	}
	
	// Retorna a coluna da casa
	public int getColuna(){
		return coluna;
	}
	
	// Retorna a peça que está na casa (pode ser null se a casa estiver vazia)
	public Peca getPeca(){
		return peca;
	}

	// Define uma nova peça para ocupar esta casa
	public void setPeca(Peca peca){
		this.peca=peca;
	}
	
	// Remove qualquer peça que estiver na casa
	public void removePeca() {
		this.peca=null;
	}
	
	 // Retorna true se a casa estiver ocupada com uma peça
	public boolean temPeca(){
		return peca != null;
	}
	
	 // Verifica se a casa está ocupada por uma peça da mesma cor passada como parâmetro
	public boolean temPecaDaMesmaCor(Cor cor) {
		return peca != null && peca.getCor() == cor;	
	}
	
	// Representação textual da casa no tabuleiro
    // Se tiver peça, retorna o toString da peça; senão retorna "--" (casa vazia)
	@Override
    public String toString() {
        return temPeca() ? peca.toString() : "--";
    }
}
