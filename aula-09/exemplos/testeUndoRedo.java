void main() {
    System.out.println("\n---------------------------------------------------------");
    System.out.println("MECANISMO DE DESFAZER E REFAZER (UNDO / REDO)");
    System.out.println("---------------------------------------------------------");

    PilhaTexto undo = criarPilha();
    PilhaTexto redo = criarPilha();

    System.out.println("\n1. Usuário realiza ações sequenciais no editor:");
    executarComando(undo, redo, "Criar Arquivo");
    executarComando(undo, redo, "Inserir Imagem");
    executarComando(undo, redo, "Escrever Título");
    executarComando(undo, redo, "Cor Magenta");
    executarComando(undo, redo, "Negrito");
    imprimirHistorico(undo, redo);

    System.out.println("\n2. Usuário pressiona Ctrl+Z duas vezes (Desfazer):");
    desfazer(undo, redo);
    desfazer(undo, redo);
    imprimirHistorico(undo, redo);

    System.out.println("\n3. Usuário pressiona Ctrl+Y uma vez (Refazer):");
    refazer(undo, redo);
    imprimirHistorico(undo, redo);

    System.out.println("\n4. Usuário digita nova edição (deve limpar a pilha Redo!):");
    executarComando(undo, redo, "Alinhar ao Centro");
    imprimirHistorico(undo, redo);
    refazer(undo, redo);
}

PilhaTexto criarPilha() {
    PilhaTexto pilha = new PilhaTexto();
    pilha.topo = null;
    return pilha;
}

void push(PilhaTexto pilha, String comando) {
    NoTexto novo = new NoTexto();
    novo.comando = comando;
    novo.proximo = pilha.topo;
    pilha.topo = novo;
}

String pop(PilhaTexto pilha) {
    if (pilha.topo == null) {
        return null;
    }
    String valor = pilha.topo.comando;
    pilha.topo = pilha.topo.proximo;
    return valor;
}

void limpar(PilhaTexto pilha) {
    pilha.topo = null;
}

void executarComando(
    PilhaTexto undo, 
    PilhaTexto redo,
    String comando
) {
    push(undo, comando);
    limpar(redo);
    System.out.println("[AÇÃO EXECUTADA]: " + comando);
}

void desfazer(PilhaTexto undo, PilhaTexto redo) {
    String comandoDesfazer = pop(undo);
    if (comandoDesfazer == null) {
        System.out.println("[UNDO: Nada para desfazer");
    } else {
        push(redo, comandoDesfazer);
        System.out.println("[CTRL+Z DESFEZ: " + comandoDesfazer);        
    }
}

void refazer(PilhaTexto undo, PilhaTexto redo) {
    String comandoRefazer = pop(redo);
    if (comandoRefazer == null) {
        System.out.println("[REDO]: Nada para refazer");
    } else {
        push(undo, comandoRefazer);
        System.out.println("[CTRL+Y REFEZ]: " + comandoRefazer);        
    }
}

void imprimirHistorico(
    PilhaTexto undo, 
    PilhaTexto redo
) {
    System.out.print("  PILHA UNDO (Histórico): ");
    NoTexto atualUndo = undo.topo;
    while (atualUndo != null) {
        System.out.print("[\"" + 
            atualUndo.comando + "\"] -> " );
        atualUndo = atualUndo.proximo;
    }
    System.out.println("null");

    System.out.print("  PILHA REDO (Histórico): ");
    NoTexto atualRedo = redo.topo;
    while (atualRedo != null) {
        System.out.print("[\"" + 
            atualRedo.comando + "\"] -> " );
        atualRedo = atualRedo.proximo;
    }
    System.out.println("null");
}