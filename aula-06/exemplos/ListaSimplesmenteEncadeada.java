import java.util.NoSuchElementException;

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

    // Remoção na cabeça da lista. Complexidade: O(1)
    public int removerInicio() {
        // Verifica se a estrutura está vazia antes de tentar a remoção (evita erro de underflow)
        if (estaVazia()) {
            throw new NoSuchElementException("Lista vazia.");
        }
        // Salva temporariamente o dado do primeiro nó para retornar ao chamador
        int dado = cabeca.dado;
        // Avança a referência da cabeça para o segundo nó (desconecta o primeiro da cadeia)
        cabeca = cabeca.proximo;
        // Decrementa o contador de nós da lista
        tamanho--;
        // Caso de borda: verifica se o nó removido era o único elemento existente
        if (tamanho == 0) {
            // Limpa a referência de cauda para que a lista fique 100% consistente e vazia
            cauda = null;
        }
        // Retorna o valor que estava armazenado no nó recém-removido
        return dado;
    }

    // Remove e retorna o elemento da última posição da lista. Complexidade: O(n)
    public int removerFim() {
        // Verifica se a lista está vazia para evitar erro de underflow
        if (estaVazia()) {
            throw new NoSuchElementException("Lista vazia.");
        }
        // Caso especial: lista com apenas um nó (cabeça e cauda são o mesmo objeto)
        if (tamanho == 1) {
            // Reaproveita a remoção do início, que já anula a cauda e zera o tamanho
            return removerInicio();
        }
        // Ponteiro auxiliar iniciado na cabeça para localizar o penúltimo nó
        Node penultimo = cabeca;
        // Navega até o nó cujo próximo elemento seja exatamente a cauda atual
        while (penultimo.proximo != cauda) {
            // Avança a referência para o próximo elo da cadeia
            penultimo = penultimo.proximo;
        }
        // Armazena temporariamente o valor do último nó antes do descarte
        int dado = cauda.dado;
        // Desconecta o último nó da cadeia apontando a referência do penúltimo para null
        penultimo.proximo = null;
        // Atualiza o ponteiro de cauda para que referencie o novo último nó
        cauda = penultimo;
        // Decrementa o contador total de nós
        tamanho--;
        // Retorna o valor primitivo do nó que foi desvinculado
        return dado;
    }

    // Remoção por valor com técnica de Dois Ponteiros. Complexidade: O(n)
    public boolean remover(int elemento) {
        // Caso base: se a lista estiver vazia, não há o que remover
        if (estaVazia()) {
            return false;
        }
        // Caso especial: o elemento está logo no primeiro nó (cabeça)
        if (cabeca.dado == elemento) {
            // Reaproveita o método específico pare remover do início
            removerInicio();
            // Retorna verdadeiro confirmando que a remoção foi concluída
            return true;
        }
        // Ponteiro que rastreia o nó predecessor ao que está sendo verificado
        Node anterior = cabeca;
        // Ponteiro de busca que começa a partir do segundo elemento
        Node atual = cabeca.proximo;
        // Itera sequencialmente enquanto houver nós a serem examinados
        while (atual != null) {
            // Compara o valor do nó corrente com o elemento buscado
            if (atual.dado == elemento) {
                // Desconecta o nó atual, ligando o anterior diretamente ao próximo nó
                anterior.proximo = atual.proximo;
                // Caso especial: verifica se o nó removido era o último elemento da lista
                if (atual == cauda) {
                    // Atualiza a referência da cauda para o nó anterior
                    cauda = anterior;
                }
                // Decrementa a contagem de nós da estrutura
                tamanho--;
                // Encerra a execução confirmando a remoção bem-sucedida
                return true;
            }
            // Avança o nó anterior para a posição atual
            anterior = atual;
            // Avança o nó atual para o próximo elo da cadeia
            atual = atual.proximo;
        }
        // Percorreu toda a lista sem encontrar o elemento procurado
        return false;
    }

    // Remove o nó em uma posição específica e retorna o seu valor. Complexidade: O(n)
    public int removerPorIndice(int indice) {
        // Valida se o índice está dentro da faixa [0, tamanho - 1]
        if (indice < 0 || indice >= tamanho) {
            throw new IndexOutOfBoundsException("Índice fora dos limites: " + indice);
        }
        // Caso especial: remoção no início (índice 0)
        if (indice == 0) {
            // Delega para a remoção inicial O(1) e retorna o valor removido
            return removerInicio();
        }
        // Caso especial: remoção no fim (último elemento)
        if (indice == tamanho - 1) {
            // Delega para a remoção final e retorna o valor removido
            return removerFim();
        }
        // Ponteiro auxiliar para parar no nó anterior ao que será removido (indice - 1)
        Node anterior = cabeca;
        // Itera até a posição imediatamente anterior ao nó alvo
        for (int i = 0; i < (indice - 1); i++) {
            // Move o ponteiro para o próximo elo
            anterior = anterior.proximo;
        }
        // Guarda a referência do nó que será desconectado da estrutura
        Node removido = anterior.proximo;
        // Faz o nó anterior apontar para o próximo do nó removido (pula o removido)
        anterior.proximo = removido.proximo;
        // Decrementa o número total de nós da lista
        tamanho--;
        // Retorna o dado contido no nó desvinculado
        return removido.dado;
    }

    // Busca linear que retorna a posição da 1ª ocorrência ou -1 se ausente. Complexidade: O(n)
    public int indexOf(int elemento) {
        // Ponteiro auxiliar posicionado na cabeça para iniciar o percurso
        Node atual = cabeca;
        // Contador sequencial para rastrear a posição do índice atual
        int indice = 0;
        // Itera enquanto houver nós válidos a serem examinados
        while (atual != null) {
            // Verifica se o valor do nó corrente é idêntico ao buscado
            if (atual.dado == elemento) {
                // Elemento localizado: retorna o índice da posição encontrada
                return indice;
            }
            // Avança para o próximo nó da lista
            atual = atual.proximo;
            // Incrementa o índice para acompanhar o avanço na estrutura
            indice++;
        }
        // Percorreu todos os nós sem encontrar o dado; retorna flag de ausência
        return -1;
    }

    // Retorna o valor armazenado em um índice sem removê-lo. Complexidade: O(n)
    public int obter(int indice) {
        // Valida se o índice requisitado pertence ao intervalo [0, tamanho - 1]
        if (indice < 0 || indice >= tamanho) {
            throw new IndexOutOfBoundsException("Índice fora dos limites: " + indice);
        }
        // Otimização em O(1): se o índice requisitado for o último da lista
        if (indice == tamanho - 1) {
            // Retorna diretamente o valor da cauda sem varrer a estrutura
            return cauda.dado;
        }
        // Ponteiro auxiliar iniciado no primeiro nó da lista
        Node atual = cabeca;
        // Itera sequencialmente até parar exatamente no nó da posição desejada
        for (int i = 0; i < indice; i++) {
            // Avança o ponteiro para o próximo nó
            atual = atual.proximo;
        }
        // Retorna o dado do nó localizado no índice
        return atual.dado;
    }

    // Altera o dado de um nó existente pelo índice informado. Complexidade: O(n)
    public void atualizar(int indice, int novoDado) {
        // Valida se o índice pertence ao intervalo válido [0, tamanho - 1]
        if (indice < 0 || indice >= tamanho) {
            throw new IndexOutOfBoundsException("Índice fora dos limites: " + indice);
        }
        // Otimização em O(1): se for a última posição, altera diretamente na cauda
        if (indice == tamanho - 1) {
            // Substitui o valor do nó referenciado por cauda
            cauda.dado = novoDado;
            // Encerra a execução antecipadamente
            return;
        }
        // Ponteiro auxiliar para navegar a partir da cabeça
        Node atual = cabeca;
        // Avança até a posição do índice correspondente
        for (int i = 0; i < indice; i++) {
            // Move a referência para o próximo elo
            atual = atual.proximo;
        }
        // Atualiza o conteúdo do nó localizado com o novo dado
        atual.dado = novoDado;
    }

    // Esvazia a lista e libera os nós para a coleta de lixo. Complexidade: O(1)
    public void limpar() {
        // Corta a referência da cabeça, tornando o primeiro nó inacessível externamente
        cabeca = null;
        // Corta a referência da cauda para não manter o último nó ancorado
        cauda = null;
        // Zera o contador de elementos para manter o estado consistente
        tamanho = 0;
    }
}
