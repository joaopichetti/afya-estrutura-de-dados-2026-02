void main() {
    System.out.println("===========================================");
    System.out.println("AULA PRÁTICA: LISTAS DUPLAMENTE ENCADEADAS");
    System.out.println("===========================================");

    ListaDupla lista = criarLista();

    System.out.println("\n1. Inserindo elementos no fim (10, 20, 30):");
    inserirFim(lista, 10);
    inserirFim(lista, 20);
    inserirFim(lista, 30);
    imprimirInicioAoFim(lista);

    System.out.println("\n2. Inserindo elemento no início (5):");
    inserirInicio(lista, 5);
    imprimirInicioAoFim(lista);

    System.out.println("\n3. Demonstrando travessia reversa (do fim ao início):");
    imprimirFimAoInicio(lista);
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
    System.out.print("Início -> ");
    NoDuplo atual = lista.inicio;
    while (atual != null) {
        System.out.print("[" + atual.valor + "] <-> ");
        atual = atual.proximo;
    }
    System.out.println("Fim (Tamanho: " + lista.tamanho + ")");
}

// 2. Travessia reversa: do fim para o início (impossível na lista simples sem pilha auxiliar)
void imprimirFimAoInicio(ListaDupla lista) {
    System.out.print("Fim -> ");
    NoDuplo atual = lista.fim;
    while (atual != null) {
        System.out.print("[" + atual.valor + "] <-> ");
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
        // O novo nó aponta para o antigo primeiro
        novo.proximo = lista.inicio;
        // O antigo primeiro aponta para trás (novo)
        lista.inicio.anterior = novo;
        // O início da lista passa a ser o novo nó
        lista.inicio = novo;
    }
    lista.tamanho++;
}