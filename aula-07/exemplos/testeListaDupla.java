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

    System.out.println("\n4. Buscando elemento 20 e removendo via referência direta em O(1):");
    NoDuplo no20 = buscar(lista, 20);
    if (no20 != null) {
        System.out.println("Nó com valor " + no20.valor + " encontrado na memória. Removendo...");
        removerNo(lista, no20);
    }
    imprimirInicioAoFim(lista);

    System.out.println("\n5. Removendo elemento da extremidade (5) por valor: ");
    removerPorValor(lista, 5);
    imprimirInicioAoFim(lista);
    removerPorValor(lista, 30);
    imprimirInicioAoFim(lista);

    // Demonstração da lista circular
    demonstrarListaCircular();
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

// Algoritmo de Busca (O(n))
// Retorna a referência direta do nó na memória (ou null se não encontrar)
NoDuplo buscar(ListaDupla lista, int valorProcurado) {
    NoDuplo atual = lista.inicio;

    while (atual != null) {
        if (atual.valor == valorProcurado) {
            // Encontrou: retorna o endereço do nó atual
            return atual;
        }
        atual = atual.proximo;
    }

    return null; // Percorreu toda a lista e não encontrou
}

void removerNo(ListaDupla lista, NoDuplo noParaRemover) {
    if (noParaRemover == null || lista.inicio == null) {
        return;
    }

    // Caso 1: O nó a remover é o primeiro elemento
    if (noParaRemover == lista.inicio) {
        lista.inicio = noParaRemover.proximo;
        if (lista.inicio != null) {
            lista.inicio.anterior = null;
        } else {
            // Se a lista só tinha 1 nó e ele foi removido,
            // o fim também vira null
            lista.fim = null;
        }
    }
    // Caso 2: O nó está no meio ou no fim
    else {
        noParaRemover.anterior.proximo = noParaRemover.proximo;
    }

    // Caso 3: O nó a remover é o último elemento
    if (noParaRemover == lista.fim) {
        lista.fim = noParaRemover.anterior;
        if (lista.fim != null) {
            lista.fim.proximo = null;
        } else {
            lista.inicio = null;
        }
    }
    // Caso 4: O nó estava no meio (possui alguém depois dele)
    else if (noParaRemover.proximo != null) {
        // O elemento seguinte aponta para trás pulando o nó removido
        noParaRemover.proximo.anterior = noParaRemover.anterior;
    }

    // Boa prática de engenharia: isolar os ponteiros do nó 
    // removido para o Garbage Collector
    noParaRemover.proximo = null;
    noParaRemover.anterior = null;
    lista.tamanho--;
}

// Remoção por Valor (O(n))
boolean removerPorValor(ListaDupla lista, int valor) {
    NoDuplo noEncontrado = buscar(lista, valor);
    if (noEncontrado != null) {
        removerNo(lista, noEncontrado);
        return true;
    }
    return false;
}

void demonstrarListaCircular() {
    System.out.println("\n-------------------------------------------");
    System.out.println("TESTANDO VARIANTE CIRCULAR (ROUND-ROBIN)");
    System.out.println("-------------------------------------------");

    ListaDupla circular = criarLista();

    // Simulação de processos de CPU recebendo tempo de processamento
    int [] processosPID = {101, 102, 103};
    for (int pid : processosPID) {
        NoDuplo novo = new NoDuplo();
        novo.valor = pid;
        if (circular.inicio == null) {
            circular.inicio = novo;
            circular.fim = novo;
            novo.proximo = novo;
        } else {
            novo.proximo = circular.inicio;
            circular.fim.proximo = novo;
            circular.fim = novo;
        }
        circular.tamanho++;
    }

    // Executando 2 ciclos completos de escalonamento
    NoDuplo cpu = circular.inicio;
    int ciclos = 6;
    System.out.println("Execução de CPU: ");
    for (int i = 0; i < ciclos; i++) {
        System.out.println("PID " + cpu.valor + "] -> ");
        cpu = cpu.proximo;
    }
    // Loop infinito
    // while (cpu != null) {
    //     System.out.println("PID " + cpu.valor + "] -> ");
    //     cpu = cpu.proximo;
    // }
    System.out.println("(ciclo contínuo)");
}