// Registro equivalente a uma "struct" em C para devolver o resultado de uma busca instrumentada
class ResultadoBusca {
    int indice;         // Posição onde o alvo foi encontrado (-1 se inexistente)
    int comparacoes;    // Quantidade de comparações realizadas com a chave alvo
}
