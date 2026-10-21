void main() {
    System.out.println("===========================================");
    System.out.println("AULA PRÁTICA 08: INTRODUÇÃO ÀS PILHAS (LIFO)");
    System.out.println("===========================================");

    System.out.println("\n1. Criando uma pilha com capacidade para 5 elementos");
    Pilha pilha = criarPilha(5);
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