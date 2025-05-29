package model;

//Classe que representa uma posição (coordenada) no tabuleiro
class Posicao {
	public final int linha; // linha da posição no tabuleiro (0 a 7)
	public final int coluna;  // coluna da posição no tabuleiro (0 a 7)
	
	// Construtor: define a linha e a coluna da posição
	public Posicao(int linha, int coluna) {
		this.linha=linha;
		this.coluna=coluna;
	}
	
	 public int getLinha() {
	    return linha;
	 }

	 public int getColuna() {
	    return coluna;
	 }
	
	// Sobrescreve o método equals para comparar posições corretamente
	@Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Posicao posicao = (Posicao) obj;

        // Retorna true se a linha e a coluna forem iguais
        return linha == posicao.linha && coluna == posicao.coluna;
    }

	// Retorna a representação textual da posição no formato (linha,coluna)
    @Override
    public String toString() {
        return "(" + linha + "," + coluna + ")";
    }
}
