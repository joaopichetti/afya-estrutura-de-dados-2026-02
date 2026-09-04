public class VetorDinamicoDouble {
    public static final int ESTRATEGIA_GEOMETRICA = 0;
    public static final int ESTRATEGIA_LINEAR = 1;

    private double[] vetor;
    private int quantidadeRegistros;
    private int estrategia;

    public VetorDinamicoDouble() {
        this(10, ESTRATEGIA_GEOMETRICA);
    }

    public VetorDinamicoDouble(int capacidadeInicial, int estrategia) {
        if (capacidadeInicial <= 0) {
            capacidadeInicial = 10;
        }
        this.vetor = new double[capacidadeInicial];
        this.estrategia = estrategia;
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
            int novaCapacidade;
            if (this.estrategia == ESTRATEGIA_GEOMETRICA) {
                novaCapacidade = this.vetor.length * 2;
            } else {
                novaCapacidade = this.vetor.length + 50;
            }
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
