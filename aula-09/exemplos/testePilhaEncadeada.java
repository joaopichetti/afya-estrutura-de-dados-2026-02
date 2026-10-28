void main() {

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