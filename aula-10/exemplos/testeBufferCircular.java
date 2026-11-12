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

    System.out.println("\n3. Consultando o próximo da fila sem remover (Operação PEEK):");
    String valorProcessar = consultarInicio(buffer);
    System.out.println("Próximo a ser processado (início): " + valorProcessar);

    System.out.println("\n4. Desenfileirando 2 registros (Operação DEQUEUE - abre vagas nos índices 0 e 1):");
    desenfileirar(buffer);
    desenfileirar(buffer);
    imprimirBufferCircular(buffer);

    System.out.println("\n5. Enfileirando novos pacotes e demonstrando o Salto Circular (Wrap-Around):");
    enfileirar(buffer, "P4");
    enfileirar(buffer, "P5");
    enfileirar(buffer, "P6");
    enfileirar(buffer, "P7");
    imprimirBufferCircular(buffer);

    System.out.println("\n6. Testando proteção contra Queue Overflow (Fila cheia):");
    enfileirar(buffer, "P8");

    demonstrarBufferSobrescrita();
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

String desenfileirar(BufferCircular buffer) {
    if (estaVazio(buffer)) {
        System.out.println("[UNDERFLOW] Fila vazia! Nada para processar.");
        return null;
    }
    String registroRemovido = buffer.valores[buffer.inicio];
    buffer.valores[buffer.inicio] = null;
    buffer.inicio = (buffer.inicio + 1) % buffer.valores.length;
    buffer.quantidade--;
    System.out.println("[DEQUEUE] Processado: " 
        + registroRemovido + " | Novo inicio=" + buffer.inicio);
        return registroRemovido;
}

String consultarInicio(BufferCircular buffer) {
    if (estaVazio(buffer)) {
        return null;
    }
    return buffer.valores[buffer.inicio];
}

void enfileirarComSobrescrita(BufferCircular buffer, String registro) {
    if (estaCheio(buffer)) {
        System.out.println("[OVERWRITE] Buffer cheio! " +
            "Sobrescrevendo evento mais antigo (" +
            buffer.valores[buffer.inicio] + ") por (" + 
            registro + ")");
        buffer.inicio = (buffer.inicio + 1) % buffer.valores.length;
    } else {
        buffer.quantidade++;
    }
    buffer.valores[buffer.fim] = registro;
    buffer.fim = (buffer.fim + 1) % buffer.valores.length;
}

void demonstrarBufferSobrescrita() {
    System.out.println("\n----------------------------------------------------------------");
    System.out.println("RING BUFFER COM SOBRESCRITA CONTÍNUA (TELEMETRIA/LOGS)");
    System.out.println("----------------------------------------------------------------");

    BufferCircular buffer = criarBufferCircular(3);
    enfileirarComSobrescrita(buffer, "LOG_01");
    enfileirarComSobrescrita(buffer, "LOG_02");
    enfileirarComSobrescrita(buffer, "LOG_03");
    imprimirBufferCircular(buffer);

    System.out.println("\nNovos eventos de telemetria chegam com o buffer lotado:");
    enfileirarComSobrescrita(buffer, "LOG_04");
    enfileirarComSobrescrita(buffer, "LOG_05");
    imprimirBufferCircular(buffer);
    String valorProcessar = consultarInicio(buffer);
    System.out.println("Evento mais antigo preservado na janela (inicio): " + valorProcessar);
}