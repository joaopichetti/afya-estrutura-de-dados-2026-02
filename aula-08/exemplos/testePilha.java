void main() {
    System.out.println("===========================================");
    System.out.println("AULA PRÁTICA 08: INTRODUÇÃO ÀS PILHAS (LIFO)");
    System.out.println("===========================================");

    System.out.println("\n1. Criando uma pilha com capacidade para 5 elementos");
    Pilha pilha = criarPilha(5);
    imprimirEstado(pilha);

    System.out.println("\n2. Empilhando elementos (Operação PUSH):");
    push(pilha, 10);
    push(pilha, 20);
    push(pilha, 30);
    imprimirEstado(pilha);

    System.out.println("\n3. Preenchendo até o limite e simulando Stack Overflow:");
    push(pilha, 40);
    push(pilha, 50);
    imprimirEstado(pilha);
    push(pilha, 60);

    System.out.println("\n4. Consultando o topo sem remover (Operação PEEK):");
    int valorTopo = peek(pilha);
    System.out.println("Elemento atualmente no topo: " + valorTopo);
    imprimirEstado(pilha);
}

Pilha criarPilha(int capacidade) {
    Pilha pilha = new Pilha();
    pilha.dados = new int[capacidade];
    pilha.topo = -1;
    return pilha;
}

boolean isEmpty(Pilha pilha) {
    return pilha.topo == -1;
}

boolean isFull(Pilha pilha) {
    return pilha.topo == (pilha.dados.length - 1);
}

int size(Pilha pilha) {
    return pilha.topo + 1;
}

boolean push(Pilha pilha, int valor) {
    if (isFull(pilha)) {
        System.out.println("[ERRO] Stack Overflow! Pilha cheia ao tentar inserir: " + valor);
        return false;
    }
    pilha.topo = pilha.topo + 1;
    pilha.dados[pilha.topo] = valor;
    System.out.println("-> push(" + valor + ") realizado com sucesso!");
    return true;
}

int peek(Pilha pilha) {
    if (isEmpty(pilha)) {
        System.out.println("[AVISO] Pilha vazia na consulta peek().");
        return -1;
    }
    return pilha.dados[pilha.topo];
}

void imprimirEstado(Pilha pilha) {
    System.out.print("Pilha (Base -> Topo): [ ");
    for (int i = 0; i <= pilha.topo; i++) {
        System.out.print(pilha.dados[i] + " ");
    }
    System.out.println("] | topo = " + pilha.topo
        + " | size = " + size(pilha)
        + "/" + pilha.dados.length
        + " | isEmpty = " + isEmpty(pilha)
        + " | isFull = " + isFull(pilha)
    );
} 