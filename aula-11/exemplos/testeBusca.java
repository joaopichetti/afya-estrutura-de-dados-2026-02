void main() {
    System.out.println("================================================================");
    System.out.println("AULA PRÁTICA 11: ALGORITMOS DE BUSCA — LINEAR VS. BINÁRIA");
    System.out.println("================================================================");

    // Demonstração da Seção 3: Busca Linear (Sequencial) em vetor desordenado
    demonstrarBuscaLinear();

    // Demonstração da Seção 3.2: Melhor, Pior e Caso Médio da Busca Linear
    demonstrarCasosBuscaLinear();

    // Demonstração da Seção 4: Busca Binária (Divisão e Conquista) em vetor ordenado
    demonstrarBuscaBinaria();

    // Demonstração da Seção 5: Violação do pré-requisito de ordenação
    demonstrarFalhaVetorDesordenado();

    // Demonstração da Seção 6: O bug do Integer Overflow no cálculo do meio
    demonstrarIntegerOverflow();
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

// =========================================================================
// SEÇÃO 4: A BUSCA BINÁRIA (DIVISÃO E CONQUISTA)
// =========================================================================

// Busca binária iterativa: a cada passo descarta metade da janela [inicio..fim]
int executarBuscaBinaria(int[] vetorOrdenado, int alvo) {
    int inicio = 0;
    int fim = vetorOrdenado.length - 1;
    while (inicio <= fim) {
        int meio = (inicio + fim) / 2;
        if (vetorOrdenado[meio] == alvo) {
            return meio; // Alvo identificado com sucesso
        }
        if (vetorOrdenado[meio] < alvo) {
            inicio = meio + 1; // O alvo está obrigatoriamente à direita do meio
        } else {
            fim = meio - 1; // O alvo está obrigatoriamente à esquerda do meio                
        }
    }
    return -1; // Janela de busca esgotada sem encontrar o alvo
}

// Mesma busca binária, imprimindo a janela [inicio..fim] e o meio a cada passo
ResultadoBusca buscaBinariaInstrumentada(int[] vetorOrdenado, int alvo) {
    ResultadoBusca resultado = new ResultadoBusca();
    resultado.indice = -1;
    resultado.comparacoes = 0;
    int inicio = 0;
    int fim = vetorOrdenado.length - 1;
    while (inicio <= fim) {
        int meio = (inicio + fim) / 2;
        resultado.comparacoes++;
        System.out.print("Passo " + resultado.comparacoes + ": janela [" + inicio + " a " + fim
                + "], meio = " + meio + " (valor " + vetorOrdenado[meio] + "). ");
        if (vetorOrdenado[meio] == alvo) {
            System.out.println(alvo + " == " + vetorOrdenado[meio] + " -> ALVO ENCONTRADO!");
            resultado.indice = meio;
            return resultado;
        }
        if (vetorOrdenado[meio] < alvo) {
            System.out.println(alvo + " > " + vetorOrdenado[meio] + " -> descarta a metade esquerda [" + inicio + ".." + meio + "]");
            inicio = meio + 1;
        } else {
            System.out.println(alvo + " < " + vetorOrdenado[meio] + " -> descarta a metade direita [" + meio + ".." + fim + "]");
            fim = meio - 1;
        }
    }
    System.out.println("Janela vazia (inicio=" + inicio + " > fim=" + fim + ") -> alvo inexistente.");
    return resultado;
}

void demonstrarBuscaBinaria() {
    System.out.println("\n----------------------------------------------------------------");
    System.out.println("SEÇÃO 4: BUSCA BINÁRIA ITERATIVA — DIVISÃO E CONQUISTA O(log n)");
    System.out.println("----------------------------------------------------------------");

    // PRÉ-REQUISITO INDISPENSÁVEL: o vetor deve estar rigorosamente ordenado
    int[] valoresOrdenados = { 5, 12, 17, 23, 42, 71, 89 };
    imprimirVetor(valoresOrdenados);

    int valorBuscado = 71;
    int posicao = executarBuscaBinaria(valoresOrdenados, valorBuscado);
    if (posicao != -1) {
        System.out.println("Elemento " + valorBuscado + " localizado no índice: " + posicao);
    } else {
        System.out.println("Elemento " + valorBuscado + " não encontrado.");
    }

    System.out.println("\nRastreando a mecânica de descarte de janelas (procurando 71):");
    buscaBinariaInstrumentada(valoresOrdenados, 71);

    System.out.println("\nMelhor caso Ω(1) — alvo exatamente no índice mediano (procurando 23):");
    buscaBinariaInstrumentada(valoresOrdenados, 23);

    System.out.println("\nPior caso O(log n) — alvo inexistente (procurando 50):");
    buscaBinariaInstrumentada(valoresOrdenados, 50);
}

// =========================================================================
// SEÇÃO 5: O PRÉ-REQUISITO CRÍTICO — ORDENAÇÃO PRÉVIA
// =========================================================================

// Verifica a monotonicidade: vetor[i] <= vetor[i + 1] para todo i — custo O(n)
boolean estaOrdenado(int[] vetor) {
    for (int i = 0; i < vetor.length - 1; i++) {
        if (vetor[i] > vetor[i + 1]) {
            return false;
        }
    }
    return true;
}

void demonstrarFalhaVetorDesordenado() {
    System.out.println("\n----------------------------------------------------------------");
    System.out.println("SEÇÃO 5: ESTUDO DE CASO — BUSCA BINÁRIA EM VETOR DESORDENADO");
    System.out.println("----------------------------------------------------------------");

    int[] desordenado = { 12, 5, 89, 43, 21, 7, 65, 33 };
    imprimirVetor(desordenado);
    System.out.println("O vetor está ordenado? " + estaOrdenado(desordenado));

    System.out.println("\nTentando localizar o valor 7 com Busca Binária:");
    ResultadoBusca rBinaria = buscaBinariaInstrumentada(desordenado, 7);

    System.out.println("\nTentando localizar o valor 7 com Busca Linear:");
    ResultadoBusca rLinear = buscalinearInstrumentada(desordenado, 7);
    imprimirResultado("Busca Linear", 7, rLinear);

    System.out.println("Busca Binária retornou índice " + rBinaria.indice
            + " | Busca Linear retornou índice " + rLinear.indice);
    System.out.println("FALHA SILENCIOSA: o 7 existe no índice " + rLinear.indice
            + ", mas a busca binária o descartou ao ir para a esquerda do 43!");
}

// =========================================================================
// SEÇÃO 6: PONTO DE ATENÇÃO EM ENGENHARIA — O BUG DO INTEGER OVERFLOW
// =========================================================================

// Expressão clássica VULNERÁVEL: a soma (inicio + fim) pode ultrapassar 2^31 - 1
int calcularMeioVulneravel(int inicio, int fim) {
    return (inicio + fim) / 2;
}

// Expressão SEGURA: (fim - inicio) é uma distância, nunca maior que 'fim'
int calcularMeioSeguro(int inicio, int fim) {
    return inicio + (fim - inicio) / 2;
}

void demonstrarIntegerOverflow() {
    System.out.println("\n----------------------------------------------------------------");
    System.out.println("SEÇÃO 6: O BUG DO INTEGER OVERFLOW NO CÁLCULO DO ÍNDICE CENTRAL");
    System.out.println("----------------------------------------------------------------");

    System.out.println("Maior int positivo (2^31 - 1): " + Integer.MAX_VALUE);

    // Simula uma janela de busca no final de um vetor com ~2 bilhões de posições
    // (não alocamos o vetor real: 2 bilhões de ints ocupariam cerca de 8 GB de RAM)
    int inicio = 1_500_000_000;
    int fim = 2_000_000_000;
    System.out.println("Janela simulada: inicio = " + inicio + ", fim = " + fim);

    int soma = inicio + fim;
    System.out.println("\ninicio + fim = " + soma + "  <-- ESTOUROU! Deveria ser 3.500.000.000");
    System.out.println("Bits da soma: " + Integer.toBinaryString(soma)
            + " (bit de sinal mais à esquerda = 1 -> negativo)");

    int meioVulneravel = calcularMeioVulneravel(inicio, fim);
    int meioSeguro = calcularMeioSeguro(inicio, fim);
    System.out.println("\nmeio = (inicio + fim) / 2          -> " + meioVulneravel + "  (índice NEGATIVO!)");
    System.out.println("meio = inicio + (fim - inicio) / 2 -> " + meioSeguro + "  (índice correto)");

    // O que aconteceria em produção ao acessar vetor[meioVulneravel]:
    int[] vetor = new int[10];
    try {
        System.out.println(vetor[meioVulneravel]);
    } catch (ArrayIndexOutOfBoundsException e) {
        System.out.println("\nAcesso a vetor[" + meioVulneravel + "] -> " + e);
    }
}