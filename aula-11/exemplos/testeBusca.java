void main() {
    System.out.println("================================================================");
    System.out.println("AULA PRÁTICA 11: ALGORITMOS DE BUSCA — LINEAR VS. BINÁRIA");
    System.out.println("================================================================");

    // Demonstração da Seção 3: Busca Linear (Sequencial) em vetor desordenado
    demonstrarBuscaLinear();
}

// =========================================================================
// SEÇÃO 3: A BUSCA LINEAR (SEQUENCIAL)
// =========================================================================

// Varredura linear exaustiva: retorna o índice da primeira ocorrência ou -1 (sentinela)
int executarBuscaLinear(int[] vetor, int valorAlvo) {
    int tamanho = vetor.length;
    for (int i = 0; i < tamanho; i++) {
        if (vetor[i] == valorAlvo) {
            return i; // Elemento localizado na posição corrente
        }
    }
    return -1; // Alvo inexistente no espaço de busca
}

// Sub-rotina utilitária para exibir o conteúdo do vetor com seus índices
void imprimirVetor(int[] vetor) {
    System.out.print("Índices: ");
    for (int i = 0; i < vetor.length; i++) {
        System.out.printf("%4d", i);
    }
    System.out.println();
    System.out.print("Valores: ");
    for (int i = 0; i < vetor.length; i++) {
        System.out.printf("%4d", vetor[i]);
    }
    System.out.println();
}

void demonstrarBuscaLinear() {
    System.out.println("\n----------------------------------------------------------------");
    System.out.println("SEÇÃO 3: BUSCA LINEAR (SEQUENCIAL) — SEM PRÉ-REQUISITO DE ORDEM");
    System.out.println("----------------------------------------------------------------");

    int[] valores = { 42, 17, 89, 5, 23, 71, 12 };
    imprimirVetor(valores);

    int valorBuscado = 23;
    int posicao = executarBuscaLinear(valores, valorBuscado);
    if (posicao != -1) {
        System.out.println("Elemento " + valorBuscado + " encontrado no índice: " + posicao);
    } else {
        System.out.println("Elemento " + valorBuscado + " não está presente no vetor.");
    }

    valorBuscado = 50;
    posicao = executarBuscaLinear(valores, posicao);
    if (posicao != -1) {
        System.out.println("Elemento " + valorBuscado + " encontrado no índice: " + posicao);
    } else {
        System.out.println("Elemento " + valorBuscado + " não está presente no vetor.");
    }
}