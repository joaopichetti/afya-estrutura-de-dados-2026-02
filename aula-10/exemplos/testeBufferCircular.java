void main() {
    System.out.println("============================================================");
    System.out.println("AULA PRÁTICA 10: FILAS (QUEUES) E BUFFERS CIRCULARES (FIFO)");
    System.out.println("============================================================");

    System.out.println("\n1. Inicializando Buffer Circular com capacidade para 5 registros:");
    BufferCircular buffer = criarBufferCircular(5);
    imprimirBufferCircular(buffer);

    System.out.println("\n2. Enfileirando os primeiros registros (Operação ENQUEUE):");
    enfileirar(buffer, "P1");
    enfileirar(buffer, "P2");
    enfileirar(buffer, "P3");
    imprimirBufferCircular(buffer);
}

BufferCircular criarBufferCircular(int capacidade) {
    BufferCircular buffer = new BufferCircular();
    buffer.valores = new String[capacidade];
    buffer.inicio = 0;
    buffer.fim = 0;
    buffer.quantidade = 0;
    return buffer;
}

boolean estaVazio(BufferCircular buffer) {
    return buffer.quantidade == 0;
}

boolean estaCheio(BufferCircular buffer) {
    return buffer.quantidade == buffer.valores.length;
}

void imprimirBufferCircular(BufferCircular buffer) {
    System.out.print("Vetor Físico: [ ");
    for (int i = 0; i < buffer.valores.length; i++) {
        boolean existeValorNoIndice = buffer.valores[i] != null;
        String valorParaImprimir = existeValorNoIndice 
            ? buffer.valores[i] : "_";
        System.out.print(valorParaImprimir + " ");
    }
    System.out.println("] (inicio=" + buffer.inicio + ", fim=" +
        buffer.fim + ", quantidade= " + buffer.quantidade + ")");
}

boolean enfileirar(BufferCircular buffer, String registro) {
    if (estaCheio(buffer)) {
        System.out.println("[OVERFLOW] Fila lotada! Rejeitando: " + registro);
        return false;        
    }
    buffer.valores[buffer.fim] = registro;
    buffer.fim = (buffer.fim + 1) % buffer.valores.length;
    buffer.quantidade++;
    System.out.println("[ENQUEUE] Inserido: " + registro + 
        " | Novo fim=" + buffer.fim);
    return true;
}