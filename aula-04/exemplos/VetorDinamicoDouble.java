public class VetorDinamicoDouble {
    private double[] vetor;
    private int quantidadeRegistros;

    public VetorDinamicoDouble() {
        this.vetor = new double[10];
        this.quantidadeRegistros = 0;
    }

    public void adicionar(double valor) {
        garantirCapacidade();
        this.vetor[this.quantidadeRegistros] = valor;
        this.quantidadeRegistros++;
    }

    public double obter(int indice) {
        validarIndice(indice);
        return this.vetor[indice];
    }

    public double remover(int indice) {
        validarIndice(indice);
        double elementoRemovido = this.vetor[indice];

        // Deslocar elementos à esquerda
        for (int i = indice; i < this.quantidadeRegistros - 1; i++) {
            this.vetor[i] = this.vetor[i + 1];
        }

        this.vetor[this.quantidadeRegistros - 1] = 0;
        this.quantidadeRegistros--;

        return elementoRemovido;
    }

    public int quantidadeRegistros() {
        return this.quantidadeRegistros;
    }

    public int capacidade() {
        return this.vetor.length;
    }

    private void garantirCapacidade() {
        if (this.quantidadeRegistros == this.vetor.length) {
            int novaCapacidade = this.vetor.length * 2;
            double[] novoVetor = new double[novaCapacidade];

            // Cópia manual dos valores
            for (int i = 0; i < this.vetor.length; i++) {
                novoVetor[i] = vetor[i];
            }

            this.vetor = novoVetor;
        }
    }

    private void validarIndice(int indice) {
        if (indice < 0 || indice >= this.quantidadeRegistros) {
            String mensagemErro = "Índice inválido: " + indice +
                ", Tamanho atual: " + this.quantidadeRegistros;
            throw new IndexOutOfBoundsException(mensagemErro);
        }
    }
}
