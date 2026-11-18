void main() {
    System.out.println("================================================================");
    System.out.println("AULA PRÁTICA 11: ALGORITMOS DE BUSCA — LINEAR VS. BINÁRIA");
    System.out.println("================================================================");

    // Demonstração da Seção 3: Busca Linear (Sequencial) em vetor desordenado
    demonstrarBuscaLinear();

    // Demonstração da Seção 3.2: Melhor, Pior e Caso Médio da Busca Linear
    demonstrarCasosBuscaLinear();
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

// =========================================================================
// SEÇÃO 3.2: ANÁLISE DE COMPLEXIDADE — CONTANDO AS COMPARAÇÕES
// =========================================================================

// Mesma busca linear, porém, contabilizando cada comparação realizada com o alvo
ResultadoBusca buscalinearInstrumentada(int[] vetor, int alvo) {
    ResultadoBusca resultado = new ResultadoBusca();
    resultado.indice = -1;
    resultado.comparacoes = 0;
    for (int i = 0; i < vetor.length; i++) {
        resultado.comparacoes++;
        if (vetor[i] == alvo) {
            resultado.indice = i;
            return resultado;
        }
    }
    return resultado;
}

void imprimirResultado(String rotulo, int alvo, ResultadoBusca r) {
    System.out.println(rotulo + " -> alvo=" + alvo + " | índice=" + r.indice
            + " | comparações=" + r.comparacoes);
}

void demonstrarCasosBuscaLinear() {
    System.out.println("\n----------------------------------------------------------------");
    System.out.println("SEÇÃO 3.2: MELHOR CASO Ω(1), PIOR CASO O(n) E CASO MÉDIO Θ(n)");
    System.out.println("----------------------------------------------------------------");

    int[] valores = { 42, 17, 89, 5, 23, 71, 12 };
    imprimirVetor(valores);

    ResultadoBusca melhorCaso = buscalinearInstrumentada(valores, 42);
    ResultadoBusca piorCasoUltimaPosicao = buscalinearInstrumentada(valores, 12);
    ResultadoBusca piorCasoInexistente = buscalinearInstrumentada(valores, 99);
    imprimirResultado("Melhor caso (alvo em [0])        ", 42, melhorCaso);
    imprimirResultado("Pior caso (alvo em [n - 1])      ", 12, piorCasoUltimaPosicao);
    imprimirResultado("Pior caso (alvo inexistente)     ", 99, piorCasoInexistente);

    // Caso médio: busca cada elemento do vetor uma vez e calcula a média de comparações
    int totalComparacoes = 0;
    for (int i = 0; i < valores.length; i++) {
        ResultadoBusca resultado = buscalinearInstrumentada(valores, valores[i]);
        totalComparacoes += resultado.comparacoes;
    }
    double media = (double) totalComparacoes / valores.length;
    System.out.println("Caso médio (todos os alvos presentes): " + media
            + " comparações | fórmula (n + 1) / 2 = " + (valores.length + 1) / 2.0);
}
