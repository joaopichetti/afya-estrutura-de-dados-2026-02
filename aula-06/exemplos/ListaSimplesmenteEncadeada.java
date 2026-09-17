public class ListaSimplesmenteEncadeada {
    // Primeiro nó da lista
    private Node cabeca;
    // Último nó da lista
    private Node cauda;
    // Contador do tamanho da lista
    private int tamanho;

    public ListaSimplesmenteEncadeada() {
        this.cabeca = null;
        this.cauda = null;
        this.tamanho = 0;
    }

    public boolean estaVazia() {
        return this.cabeca == null;
    }

    public int getTamanho() {
        return this.tamanho;
    }

    // Inserção na cabeça da estrutura. Complexidade: O(1)
    public void inserirInicio(int dado) {
        // Instancia o novo nó fazendo seu próximo apontar para a cabeça atual
        Node novo = new Node(dado, cabeca);
        // Redireciona a referência de cabeça para que o novo nó seja o primeiro elemento
        cabeca = novo;
        // Caso especial: verifica se a lista estava vazia antes desta inserção
        if (tamanho == 0) {
            // Sendo o único nó existente, a cauda também deve apontar para ele
            cauda = novo;
        }
        // Incrementa o contador total de elementos da lista
        tamanho++;
    }

    // Inserção no final com cauda otimizada. Complexidade: O(1)
    public void inserirFim(int dado) {
        // Instancia o novo nó (seu próximo será null por padrão, já que ficará no final)
        Node novo = new Node(dado);
        // Verifica se a estrutura está vazia para tratar o caso de borda inicial
        if (estaVazia()) {
            // Como é o primeiro elemento inserido, a cabeça aponta para o novo nó
            cabeca = novo;
            // A cauda também aponta para o novo nó (ele é o primeiro e o último simultaneamente)
            cauda = novo;
        } else {
            // Conecta o último nó atual ao novo nó, estendendo a cadeia
            cauda.proximo = novo;
            // Atualiza o ponteiro de cauda para que referencie o novo término da lista
            cauda = novo;
        }
        // Incrementa o contador total de elementos da lista
        tamanho++;
    }

    // Inserção por índice arbitrário. Complexidade: O(n)
    public void inserir(int indice, int dado) {
        // Valida se o índice está dentro do intervalo permitido [0, tamanho]
        if (indice < 0 || indice > tamanho) {
            throw new IndexOutOfBoundsException("Índice fora dos limites: " + indice);
        }
        // Caso especial: inserção no início da lista
        if (indice == 0) {
            inserirInicio(dado);
            return;
        }
        // Caso especial: inserção no final da lista
        if (indice == tamanho) {
            inserirFim(dado);
            return;
        }
        // Ponteiro auxiliar iniciado no primeiro nó para navegar na lista
        Node anterior = cabeca;
        // Percorre a lista até parar exatamente no nó da posição (indice - 1)
        for (int i = 0; i < (indice - 1); i++) {
            // Avança a referência para o próximo nó
            anterior = anterior.proximo;
        }
        // Cria o novo nó apontando seu próximo para o nó que atualmente ocupa a posição
        Node novo = new Node(dado, anterior.proximo);
        // Conecta o nó anterior ao novo nó, inserindo-o na cadeia
        anterior.proximo = novo;
        tamanho++;
    }

    // Retorna uma representação legível do encadeamento para depuração. Complexidade: O(n)
    @Override
    public String toString() {
        // Verifica se a lista não possui nós cadastrados
        if (estaVazia()) {
            // Retorna colchetes vazios indicando ausência de dados
            return "[]";
        }
        String representacao = "[";
        // Ponteiro auxiliar iniciado no primeiro nó da lista
        Node atual = cabeca;
        // Percorre sequencialmente toda a lista até atingir null
        while (atual != null) {
            // Concatena o valor do nó corrente na string de representação
            representacao += atual.dado;
            // Verifica se este nó não é o último da lista
            if (atual.proximo != null) {
                // Adiciona a representação visual da seta do ponteiro
                representacao += " -> ";
            }
            // Avança para o próximo nó da cadeia
            atual = atual.proximo;
        }
        // Fecha a representação textual com o colchete final
        representacao += "]";
        // Converte o buffer montado para String e retorna ao chamador
        return representacao;
    }
}
