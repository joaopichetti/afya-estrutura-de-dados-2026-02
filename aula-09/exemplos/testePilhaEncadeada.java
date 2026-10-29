void main() {
    System.out.println("=========================================================");
    System.out.println("AULA PRÁTICA 09: PILHAS COM LISTAS ENCADEADAS E APLICAÇÕES");
    System.out.println("=========================================================");

    System.out.println("\n1. Criando uma pilha dinâmica encadeada:");
    PilhaDinamica pilha = criarPilha();
    imprimirEstado(pilha);

    System.out.println("\n2. Empilhando elementos na cabeça da lista em O(1) (Operação PUSH):");
    push(pilha, 7);
    push(pilha, 18);
    push(pilha, 42);
    imprimirEstado(pilha);

    System.out.println("\n3. Consultando o elemento no topo sem remover (Operação PEEK):");
    int valorTopo = peek(pilha);
    System.out.println("Elemento atualmente no topo: " + valorTopo);
    imprimirEstado(pilha);

    System.out.println("\n4. Desempilhando elementos em ordem LIFO em O(1) (Operação POP):");
    System.out.println("-> pop() removeu: " + pop(pilha));
    imprimirEstado(pilha);

    System.out.println("\n5. Esvaziando a pilha e testando proteção contra Stack Underflow:");
    while (!estaVazia(pilha)) {
        System.out.println("-> pop() removeu: " + pop(pilha));
    }
    imprimirEstado(pilha);
    pop(pilha);
}

PilhaDinamica criarPilha() {
    PilhaDinamica pilha = new PilhaDinamica();
    pilha.topo = null;
    pilha.tamanho = 0;
    return pilha;
}

boolean estaVazia(PilhaDinamica pilha) {
    return pilha.topo == null;
}

void imprimirEstado(PilhaDinamica pilha) {
    System.out.print("Pilha.topo -> ");
    No atual = pilha.topo;
    while (atual != null) {
        System.out.print("[" + atual.valor + "] -> ");
        atual = atual.proximo;
    }
    System.out.println("null | tamanho = " + 
        pilha.tamanho + " | estaVazia = " +
        estaVazia(pilha)
    );
}

void push(PilhaDinamica pilha, int valor) {
    No novoNo = new No();
    novoNo.valor = valor;
    novoNo.proximo = pilha.topo;
    pilha.topo = novoNo;
    pilha.tamanho++;
    System.out.println("-> push(" + valor + 
        ") realizado na cabeça da lista.");
}

int peek(PilhaDinamica pilha) {
    // ==== if-else tradicional ====
    // if (estaVazia(pilha)) {
    //     return -1;
    // } else {
    //     return pilha.topo.valor;
    // }
    // ==== operador ternário ====
    return estaVazia(pilha) ? -1 : pilha.topo.valor;
}

int pop(PilhaDinamica pilha) {
    if (estaVazia(pilha)) {
        System.out.println("ERRO: Stack Underflow (Pilha vazia)");
        return -1;
    }
    int valorRemovido = pilha.topo.valor;
    pilha.topo = pilha.topo.proximo;
    pilha.tamanho--;
    return valorRemovido;
}