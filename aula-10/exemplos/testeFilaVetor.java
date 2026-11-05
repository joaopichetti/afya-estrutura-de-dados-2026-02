void main() {
    System.out.println("============================================================");
    System.out.println("AULA PRÁTICA 10: FILAS (QUEUES) E BUFFERS CIRCULARES (FIFO)");
    System.out.println("============================================================");
    
    System.out.println("\n1. Criação de fila e impressão do estado inicial");
    FilaVetor fila = criarFilaVetor(5);
    imprimirFilaVetor(fila);

    System.out.println("\n2. Enfileirando valores na fila");
    enqueueFilaVetor(fila, 10);
    imprimirFilaVetor(fila);
    enqueueFilaVetor(fila, 20);
    imprimirFilaVetor(fila);
    enqueueFilaVetor(fila, 30);
    imprimirFilaVetor(fila);
    enqueueFilaVetor(fila, 40);
    imprimirFilaVetor(fila);
    enqueueFilaVetor(fila, 50);
    imprimirFilaVetor(fila);
    enqueueFilaVetor(fila, 60);

    System.out.println("\n3. Consultando valor a ser removido");
    int valorRemover = peekFilaVetor(fila);
    System.out.println("Valor a remover: " + valorRemover);
    imprimirFilaVetor(fila);

    System.out.println("\n4. Removendo primeiro elemento (exige deslocar todos os demais na RAM):");
    int valorRemovido = dequeueFilaVetor(fila);
    System.out.println("Valor removido: " + valorRemovido +
        " (Deslocou 20, 30, 40 e 50 uma casa para a esquerda!)");
    imprimirFilaVetor(fila);
}

FilaVetor criarFilaVetor(int capacidade) {
    FilaVetor fila = new FilaVetor();
    fila.valores = new int[capacidade];
    fila.fim = 0;
    return fila;
}

void imprimirFilaVetor(FilaVetor fila) {
    System.out.print("Vetor (Início fixo em [0]): [ ");
    for (int i = 0; i < fila.valores.length; i++) {
        if (i < fila.fim) {
            System.out.print(fila.valores[i] + " ");
        } else {
            System.out.print("_ ");
        }
    }
    System.out.println("] (fim=" + fila.fim + ")");
}

boolean enqueueFilaVetor(FilaVetor fila, int valor) {
    if (fila.fim == fila.valores.length) {
        System.out.println("Erro: Fila cheia! Não foi possível inserir " + valor);
        return false;
    }
    fila.valores[fila.fim] = valor;
    fila.fim++;
    System.out.println("-> enqueueFilaVetor(" + 
        valor + ") realizado no índice " + 
        (fila.fim - 1));
    return true;
}

int dequeueFilaVetor(FilaVetor fila) {
    if (fila.fim == 0) {
        System.out.println("Erro: Fila vazia!");
        return -1;
    }
    int valorRetornar = fila.valores[0];
    // Gargalo O(n): desloca todos os elementos restantes uma posição para a esquerda
    for (int i = 0; i < fila.fim - 1; i++) {
        fila.valores[i] = fila.valores[i + 1];
    }
    fila.fim--;
    return valorRetornar;
}

int peekFilaVetor(FilaVetor fila) {
    if (fila.fim == 0) {
        System.out.println("Erro: Fila vazia!");
        return -1;
    }
    return fila.valores[0];
}