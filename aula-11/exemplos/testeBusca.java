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

    // Demonstração da Seção 7: Comparativo de complexidade em escala crescente
    demonstrarComparativoEscala();

    // Demonstração da Seção 8.1: Ponto de equilíbrio (break-even) entre ordenar e buscar
    demonstrarPontoEquilibrio();

    // Demonstração da Seção 8.2: Paralelo com SGBDs — Full Table Scan vs. Index Seek
    demonstrarCatalogoProdutos();
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

// =========================================================================
// SEÇÃO 7: COMPARATIVO DE COMPLEXIDADE E ESCALA COMPUTACIONAL
// =========================================================================

// Busca binária contabilizando comparações, sem impressões (para vetores grandes)
ResultadoBusca buscaBinariaInstrumentadaSemLog(int[] vetorOrdenado, int alvo) {
    ResultadoBusca r = new ResultadoBusca();
    r.indice = -1;
    r.comparacoes = 0;
    int inicio = 0;
    int fim = vetorOrdenado.length - 1;
    while (inicio <= fim) {
        int meio = inicio + (fim - inicio) / 2;
        r.comparacoes++;
        if (vetorOrdenado[meio] == alvo) {
            r.indice = meio;
            return r;
        }
        if (vetorOrdenado[meio] < alvo) {
            inicio = meio + 1;
        } else {
            fim = meio - 1;
        }
    }
    return r;
}

// Gera um vetor já ordenado com os números pares 0, 2, 4, ..., 2(n - 1)
int[] gerarVetorOrdenado(int n) {
    int[] vetor = new int[n];
    for (int i = 0; i < n; i++) {
        vetor[i] = i * 2;
    }
    return vetor;
}

void demonstrarComparativoEscala() {
    System.out.println("\n----------------------------------------------------------------");
    System.out.println("SEÇÃO 7: COMPARATIVO O(n) VS. O(log n) EM ESCALA CRESCENTE");
    System.out.println("----------------------------------------------------------------");
    System.out.println("Pior caso: buscando um valor ímpar (inexistente) maior que todos os elementos.\n");

    int[] tamanhos = { 1_000, 100_000, 1_000_000, 10_000_000, 100_000_000 };

    System.out.printf("%13s | %13s | %9s | %8s | %10s | %10s | %10s%n",
            "n", "Linear (cmp)", "Bin (cmp)", "⌈log₂ n⌉", "Fator", "Linear(ms)", "Bin(ms)");
    for (int t = 0; t < tamanhos.length; t++) {
        int n = tamanhos[t];
        int[] vetor = gerarVetorOrdenado(n);
        int alvoInexistente = 2 * n + 1;

        long t0 = System.nanoTime();
        ResultadoBusca rLinear = buscalinearInstrumentada(vetor, alvoInexistente);
        long t1 = System.nanoTime();
        ResultadoBusca rBinaria = buscaBinariaInstrumentadaSemLog(vetor, alvoInexistente);
        long t2 = System.nanoTime();

        int log2Teto = (int) Math.ceil(Math.log(n) / Math.log(2));
        long fator = rLinear.comparacoes / rBinaria.comparacoes;
        System.out.printf("%,13d | %,13d | %9d | %8d | %,9dx | %10.3f | %10.4f%n",
                n, rLinear.comparacoes, rBinaria.comparacoes, log2Teto, fator,
                (t1 - t0) / 1_000_000.0, (t2 - t1) / 1_000_000.0);
    }
    System.out.println("\nCada vez que n dobra: a linear dobra o trabalho, a binária faz só +1 comparação.");
}

// =========================================================================
// SEÇÃO 8.1: VISÃO DO ARQUITETO — PONTO DE EQUILÍBRIO (BREAK-EVEN)
// =========================================================================

// Gera um lote de n valores desordenados (semente fixa para resultados reprodutíveis)
int[] gerarVetorDesordenado(int n, Random gerador) {
    int[] vetor = new int[n];
    for (int i = 0; i < n; i++) {
        vetor[i] = gerador.nextInt(2 * n);
    }
    return vetor;
}

// Abordagem 1: k buscas lineares diretas no lote desordenado -> O(k · n)
long medirAbordagemLinear(int[] lote, int[] consultas) {
    long t0 = System.nanoTime();
    for (int i = 0; i < consultas.length; i++) {
        executarBuscaLinear(lote, consultas[i]);
    }
    return System.nanoTime() - t0;
}

// Abordagem 2: ordena uma única vez e faz k buscas binárias -> O(n log n) + k · O(log n)
long medirAbordagemOrdenarEBuscar(int[] lote, int[] consultas) {
    long t0 = System.nanoTime();
    int[] copia = Arrays.copyOf(lote, lote.length); // Preserva o lote original
    Arrays.sort(copia); // Ordenação da biblioteca padrão: O(n log n)
    for (int i = 0; i < consultas.length; i++) {
        executarBuscaBinaria(copia, consultas[i]);
    }
    return System.nanoTime() - t0;
}

void demonstrarPontoEquilibrio() {
    System.out.println("\n----------------------------------------------------------------");
    System.out.println("SEÇÃO 8.1: PONTO DE EQUILÍBRIO — BUSCA LINEAR VS. ORDENAR + BINÁRIA");
    System.out.println("----------------------------------------------------------------");

    Random gerador = new Random(2026);
    int n = 100_000;
    int[] lote = gerarVetorDesordenado(n, gerador);
    System.out.println("Lote recebido pelo microsserviço: n = " + n + " itens desordenados.\n");

    int[] quantidadesConsultas = { 1, 10, 100, 1_000, 10_000 };

    System.out.printf("%8s | %20s | %22s | %s%n", "k", "1) Linear (ms)", "2) Ordenar+Bin (ms)", "Vencedora");
    for (int q = 0; q < quantidadesConsultas.length; q++) {
        int k = quantidadesConsultas[q];
        int[] consultas = new int[k];
        for (int i = 0; i < k; i++) {
            consultas[i] = gerador.nextInt(2 * n); // Chaves no mesmo intervalo do lote
        }

        long tempoLinear = medirAbordagemLinear(lote, consultas);
        long tempoOrdenado = medirAbordagemOrdenarEBuscar(lote, consultas);
        String vencedora = (tempoLinear < tempoOrdenado) ? "Linear direta" : "Ordenar + Binária";

        System.out.printf("%,8d | %20.3f | %22.3f | %s%n",
                k, tempoLinear / 1_000_000.0, tempoOrdenado / 1_000_000.0, vencedora);
    }
    System.out.println("\nPoucas consultas (k pequeno): ordenar não compensa.");
    System.out.println("Muitas consultas (k >> 1): o custo O(n log n) da ordenação é amortizado.");
}

// =========================================================================
// SEÇÃO 8.2: PARALELO COM BANCOS DE DADOS — FULL TABLE SCAN VS. INDEX SEEK
// =========================================================================

// Popula a "tabela" de produtos já ordenada pela chave primária 'codigo'
Produto[] criarCatalogo(int quantidade) {
    Produto[] catalogo = new Produto[quantidade];
    for (int i = 0; i < quantidade; i++) {
        Produto p = new Produto();
        p.codigo = 1000 + i * 3; // Códigos crescentes, porém não consecutivos
        p.nome = "PROD-" + Integer.toHexString(p.codigo * 7919).toUpperCase(); // Nomes sem ordem alguma
        p.preco = 10.0 + (i % 500);
        catalogo[i] = p;
    }
    return catalogo;
}

// SELECT * FROM produtos WHERE nome = ?  (coluna sem índice -> Full Table Scan, O(n))
ResultadoBusca buscarProdutoPorNome(Produto[] catalogo, String nome) {
    ResultadoBusca r = new ResultadoBusca();
    r.indice = -1;
    r.comparacoes = 0;
    for (int i = 0; i < catalogo.length; i++) {
        r.comparacoes++;
        if (catalogo[i].nome.equals(nome)) {
            r.indice = i;
            return r;
        }
    }
    return r;
}

// SELECT * FROM produtos WHERE codigo = ?  (coluna indexada/ordenada -> Index Seek, O(log n))
ResultadoBusca buscarProdutoPorCodigo(Produto[] catalogo, int codigo) {
    ResultadoBusca r = new ResultadoBusca();
    r.indice = -1;
    r.comparacoes = 0;
    int inicio = 0;
    int fim = catalogo.length - 1;
    while (inicio <= fim) {
        int meio = inicio + (fim - inicio) / 2;
        r.comparacoes++;
        if (catalogo[meio].codigo == codigo) {
            r.indice = meio;
            return r;
        }
        if (catalogo[meio].codigo < codigo) {
            inicio = meio + 1;
        } else {
            fim = meio - 1;
        }
    }
    return r;
}

void imprimirProdutoEncontrado(Produto[] catalogo, ResultadoBusca r) {
    if (r.indice == -1) {
        System.out.println("   Nenhum produto encontrado (" + r.comparacoes + " comparações).");
        return;
    }
    Produto p = catalogo[r.indice];
    System.out.printf("   Linha %d -> {codigo: %d, nome: '%s', preco: %.2f} | %,d comparações%n",
            r.indice, p.codigo, p.nome, p.preco, r.comparacoes);
}

void demonstrarCatalogoProdutos() {
    System.out.println("\n----------------------------------------------------------------");
    System.out.println("SEÇÃO 8.2: ESTUDO DE CASO — CATÁLOGO DE PRODUTOS (FULL SCAN VS. INDEX SEEK)");
    System.out.println("----------------------------------------------------------------");

    Produto[] catalogo = criarCatalogo(1_000_000);
    System.out.println("Tabela 'produtos' carregada com " + catalogo.length + " linhas (ordenada por codigo).");

    // Escolhe um produto próximo ao final da tabela para consultar pelos dois caminhos
    Produto alvo = catalogo[catalogo.length - 10];

    System.out.println("\nSELECT * FROM produtos WHERE nome = '" + alvo.nome + "';  -- Full Table Scan");
    imprimirProdutoEncontrado(catalogo, buscarProdutoPorNome(catalogo, alvo.nome));

    System.out.println("\nSELECT * FROM produtos WHERE codigo = " + alvo.codigo + ";  -- Index Seek");
    imprimirProdutoEncontrado(catalogo, buscarProdutoPorCodigo(catalogo, alvo.codigo));

    System.out.println("\nSELECT * FROM produtos WHERE codigo = 1001;  -- código inexistente");
    imprimirProdutoEncontrado(catalogo, buscarProdutoPorCodigo(catalogo, 1001));

    System.out.println("\nMesmo registro, mesmo resultado — a diferença está apenas no caminho de acesso.");
}