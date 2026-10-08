void main() {

}

// Função para instanciar e zerar os ponteiros da lista
ListaDupla criarLista() {
    ListaDupla lista = new ListaDupla();
    lista.inicio = null;
    lista.fim = null;
    lista.tamanho = 0;
    return lista;
}

// 1. Travessia tradicional: do início ao fim
void imprimirInicioAoFim(ListaDupla lista) {
    System.out.println("Início -> ");
    NoDuplo atual = lista.inicio;
    while (atual != null) {
        System.out.println("[" + atual.valor + "] <-> ");
        atual = atual.proximo;
    }
    System.out.println("Fim (Tamanho: " + lista.tamanho + ")");
}

// 2. Travessia reversa: do fim para o início (impossível na lista simples sem pilha auxiliar)
void imprimirFimAoInicio(ListaDupla lista) {
    System.out.println("Fim -> ");
    NoDuplo atual = lista.fim;
    while (atual != null) {
        System.out.println("[" + atual.valor + "] <-> ");
        atual = atual.anterior;
    }
    System.out.println("Início (Tamanho: " + lista.tamanho + ")");
}