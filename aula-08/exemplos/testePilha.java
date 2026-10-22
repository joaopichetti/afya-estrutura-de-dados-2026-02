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
    push(pilha, 60); // Deve exibir erro de Stack Overflow

    System.out.println("\n4. Consultando o topo sem remover (Operação PEEK):");
    int valorTopo = peek(pilha);
    System.out.println("Elemento atualmente no topo: " + valorTopo);
    imprimirEstado(pilha); // Mostra que a pilha permanece inalterada

    System.out.println("\n5. Desempilhando elementos em ordem LIFO (Operação POP):");
    int valorRemovido = pop(pilha);
    System.out.println("-> pop() removeu: " + valorRemovido);
    valorRemovido = pop(pilha);
    System.out.println("-> pop() removeu: " + valorRemovido);
    imprimirEstado(pilha);

    while (!isEmpty(pilha)) {
        valorRemovido = pop(pilha);
        System.out.println("-> pop() removeu: " + valorRemovido);        
    }
    imprimirEstado(pilha);
    pop(pilha); // Deve exibir erro de Stack Underflow
}

// Função para instanciar e inicializar a estrutura da pilha
Pilha criarPilha(int capacidade) {
    Pilha pilha = new Pilha();
    pilha.dados = new int[capacidade];
    pilha.topo = -1;
    return pilha;
}

// Verifica se a pilha está vazia — Complexidade: O(1)
boolean isEmpty(Pilha pilha) {
    return pilha.topo == -1;
}

// Verifica se a pilha atingiu o limite físico do vetor — Complexidade: O(1)
boolean isFull(Pilha pilha) {
    return pilha.topo == (pilha.dados.length - 1);
}

// Retorna o número atual de elementos empilhados — Complexidade: O(1)
int size(Pilha pilha) {
    return pilha.topo + 1;
}

// Operação PUSH: Insere um elemento no topo da pilha — Complexidade: O(1)
boolean push(Pilha pilha, int valor) {
    if (isFull(pilha)) {
        System.out.println("[ERRO] Stack Overflow! Pilha cheia ao tentar inserir: " + valor);
        return false;
    }
    pilha.topo = pilha.topo + 1;        // Avança o indicador para a próxima gaveta livre
    pilha.dados[pilha.topo] = valor;    // Grava o dado na nova posição de topo
    System.out.println("-> push(" + valor + ") realizado com sucesso!");
    return true;
}

// Operação PEEK: Consulta o elemento do topo sem desempilhar — Complexidade: O(1)
int peek(Pilha pilha) {
    if (isEmpty(pilha)) {
        System.out.println("[AVISO] Pilha vazia na consulta peek().");
        return -1;
    }
    return pilha.dados[pilha.topo];
}

// Operação POP: Remove e retorna o elemento do topo — Complexidade: O(1)
int pop(Pilha pilha) {
    if (isEmpty(pilha)) {
        System.out.println("[ERRO] Stack Underflow! Não é possível remover de pilha vazia.");
        return -1; // Valor sentinela de erro
    }
    int valorRemovido = peek(pilha);
    pilha.dados[pilha.topo] = 0;
    pilha.topo = pilha.topo - 1; // Recua o topo (remoção lógica imediata em O(1))
    return valorRemovido;
}

// Função auxiliar para visualizar o estado atual da pilha na memória
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