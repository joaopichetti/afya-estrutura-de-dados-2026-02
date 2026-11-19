// Registro equivalente a uma "struct" em C para uma linha da tabela de produtos do e-commerce
class Produto {
    int codigo;   // Chave primária (a tabela é mantida ordenada por este campo — "índice")
    String nome;  // Coluna sem índice: não há nenhuma ordem garantida por nome
    double preco; // Preço unitário do produto
}