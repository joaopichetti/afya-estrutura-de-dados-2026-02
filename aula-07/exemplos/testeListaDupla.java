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

// Inserção no Fim (O(1))
void inserirFim(ListaDupla lista, int valor) {
    // 1. Aloca nova memória para o nó
    NoDuplo novo = new NoDuplo();
    novo.valor = valor;
    novo.proximo = null;
    novo.anterior = null;

    // Caso A: A lista está vazia
    if (lista.inicio == null) {
        lista.inicio = novo;
        lista.fim = novo;
    } 
    // Caso B: A lista já possui um ou mais elementos
    else {
        // O anterior do novo nó é o antigo último
        novo.anterior = lista.fim;
        // O antigo último agora aponta para o novo
        lista.fim.proximo = novo;
        // O ponteiro de fim da lista avança
        lista.fim = novo;
    }
    lista.tamanho++;
}

void inserirInicio(ListaDupla lista, int valor) {
    // 1. Aloca nova memória para o nó
    NoDuplo novo = new NoDuplo();
    novo.valor = valor;
    novo.anterior = null;
    novo.proximo = null;

    // Caso A: A lista está vazia
    if (lista.inicio == null) {
        lista.inicio = novo;
        lista.fim = novo;
    } 
    // Caso B: A lista já possui um ou mais elementos
    else {
        novo.proximo = lista.inicio;
        lista.inicio.anterior = novo;
        lista.inicio = novo;
    }
    lista.tamanho++;
}