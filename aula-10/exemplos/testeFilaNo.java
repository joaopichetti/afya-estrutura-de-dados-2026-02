void main() {
    System.out.println("============================================================");
    System.out.println("AULA PRÁTICA 10: FILAS (QUEUES) E BUFFERS CIRCULARES (FIFO)");
    System.out.println("============================================================");

    System.out.println("\n1. Criação de fila e impressão do estado inicial");
    FilaNo fila = criarFilaNo();
    imprimirFilaNo(fila);

    System.out.println("\n2. Enfileirando valores na fila");
    enqueueFilaNo(fila, "Paciente 01");
    imprimirFilaNo(fila);
    enqueueFilaNo(fila, "Paciente 02");
    imprimirFilaNo(fila);
    enqueueFilaNo(fila, "Paciente 03");
    imprimirFilaNo(fila);
    enqueueFilaNo(fila, "Paciente 04");
    imprimirFilaNo(fila);
    enqueueFilaNo(fila, "Paciente 05");
    imprimirFilaNo(fila);
    enqueueFilaNo(fila, "Paciente 06");
    imprimirFilaNo(fila);
    enqueueFilaNo(fila, "Paciente 07");
    imprimirFilaNo(fila);
    enqueueFilaNo(fila, "Paciente 08");
    imprimirFilaNo(fila);

    System.out.println("\n3. Consultando valor a ser removido");
    String valorRemover = peekFilaNo(fila);
    System.out.println("Valor a remover: " + valorRemover);
    imprimirFilaNo(fila);
    
    System.out.println("\n4. Removendo primeiro elemento em operação constante (O(1))");
    String valorRemovido = dequeueFilaNo(fila);
    System.out.println("Removido: " + valorRemovido);
    imprimirFilaNo(fila);

    System.out.println("\n5. Esvaziando toda a fila");
    while (fila.cabeca != null) {
        valorRemovido = dequeueFilaNo(fila);
        System.out.println("Removido: " + valorRemovido);
        imprimirFilaNo(fila);
    }

    dequeueFilaNo(fila);
}

FilaNo criarFilaNo() {
    FilaNo fila = new FilaNo();
    fila.cabeca = null;
    fila.cauda = null;
    fila.tamanho = 0;
    return fila;
}

void imprimirFilaNo(FilaNo fila) {
    System.out.print("Fila Dinâmica (cabeça -> cauda): ");
    No atual = fila.cabeca;
    while (atual != null) {
        System.out.print("[\"" + atual.valor + "\"] -> ");
        atual = atual.proximo;
    }
    System.out.println("null | tamanho = " + fila.tamanho);
}

void enqueueFilaNo(FilaNo fila, String valor) {
    No novoNo = new No();
    novoNo.valor = valor;
    novoNo.proximo = null;
    if (fila.cabeca == null) {
        fila.cabeca = novoNo;
        fila.cauda = novoNo;
    } else {
        fila.cauda.proximo = novoNo;
        fila.cauda = novoNo;
    }
    fila.tamanho++;
    System.out.println("-> enqueueFilaNo(\"" + 
        valor + "\") alocado dinamicamente na Heap.");
}

String dequeueFilaNo(FilaNo fila) {
    if (fila.cabeca == null) {
        System.out.println("Erro: Fila vazia!");
        return null;
    }
    String valorRemover = fila.cabeca.valor;
    fila.cabeca = fila.cabeca.proximo;
    if (fila.cabeca == null) {
        fila.cauda = null;
    }
    fila.tamanho--;
    return valorRemover;
}

String peekFilaNo(FilaNo fila) {
    return (fila.cabeca == null)
        ? null : fila.cabeca.valor;
}