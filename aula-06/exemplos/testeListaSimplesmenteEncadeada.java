void main() {
    ListaSimplesmenteEncadeada lista = new ListaSimplesmenteEncadeada();
    System.out.println("--- 1. ESTADO INICIAL ---");
    System.out.println("Lista: " + lista);
    System.out.println("Está vazia? " + lista.estaVazia());
    System.out.println("Tamanho: " + lista.getTamanho());
    System.out.println();

    System.out.println("--- 2. TESTE DE INSERÇÕES ---");
    System.out.println("-> Inserindo 20 no início");
    lista.inserirInicio(20);
    System.out.println("Estado: " + lista + " | Tamanho: " + lista.getTamanho());

    System.out.println("-> Inserindo 10 no início");
    lista.inserirInicio(10);
    System.out.println("Estado: " + lista + " | Tamanho: " + lista.getTamanho());

    System.out.println("-> Inserindo 40 no fim");
    lista.inserirFim(40);
    System.out.println("Estado: " + lista + " | Tamanho: " + lista.getTamanho());

    System.out.println("-> Inserindo 50 no fim");
    lista.inserirFim(50);
    System.out.println("Estado: " + lista + " | Tamanho: " + lista.getTamanho());

    System.out.println("-> Inserindo 30 no índice 2 (meio da lista)");
    lista.inserir(2, 30);
    System.out.println("Estado: " + lista + " | Tamanho: " + lista.getTamanho());

    System.out.println("-> Inserindo 5 no índice 0 (delega para inserirInicio)");
    lista.inserir(0, 5);
    System.out.println("Estado: " + lista + " | Tamanho: " + lista.getTamanho());

    System.out.println("-> Inserindo 60 no índice " + lista.getTamanho() + " (delega para inserirFim)");
    lista.inserir(lista.getTamanho(), 60);
    System.out.println("Estado: " + lista + " | Tamanho: " + lista.getTamanho());
    System.out.println();

    System.out.println("--- 3. CONSULTAS E BUSCAS ---");
    System.out.println("Elemento no índice 0 (cabeça): " + lista.obter(0));
    System.out.println("Elemento no índice 3 (meio): " + lista.obter(3));
    System.out.println("Elemento no índice final (cauda): " + lista.obter(lista.getTamanho() - 1));
    System.out.println("Índice do valor 30: " + lista.indexOf(30));
    System.out.println("Índice do valor 99 (inexistente): " + lista.indexOf(99));
    System.out.println();
    
    System.out.println("--- 4. REMOÇÕES ---");
    System.out.println("-> removerInicio(): retirado " + lista.removerInicio());
    System.out.println("Estado: " + lista + " | Tamanho: " + lista.getTamanho());

    System.out.println("-> removerFim(): retirado " + lista.removerFim());
    System.out.println("Estado: " + lista + " | Tamanho: " + lista.getTamanho());

    System.out.println("-> removerPorIndice(2): retirado " + lista.removerPorIndice(2));
    System.out.println("Estado: " + lista + " | Tamanho: " + lista.getTamanho());

    System.out.println("-> remover(35) [por valor existente]: " + lista.remover(35));
    System.out.println("Estado: " + lista + " | Tamanho: " + lista.getTamanho());

    System.out.println("-> remover(99) [por valor inexistente]: " + lista.remover(99));
    System.out.println("Estado: " + lista + " | Tamanho: " + lista.getTamanho());
    System.out.println();
}