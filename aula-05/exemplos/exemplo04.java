void main() {
    int[] array1 = { 10 };
    int[] array2 = new Random().ints(1_000, 1, 101).toArray();
    int[] array3 = new Random().ints(1_000_000, 1, 101).toArray();
    int[] array4 = new Random().ints(1_000_000_000, 1, 1001).toArray();
    buscaBinaria(array1, 999_999_999);
    buscaBinaria(array2, 999_999_999);
    buscaBinaria(array3, 999_999_999);
    buscaBinaria(array4, 999_999_999);
}

// Exemplo Complexidade Logarítmica O(log n)
int buscaBinaria(int[] arr, int elemento) {
    long antes = System.nanoTime();

    int inicio = 0;
    int fim = arr.length - 1;
    int comparacoes = 0;
    while (inicio <= fim) {
        comparacoes++;
        int meio = inicio + (fim - inicio) / 2;
        if (arr[meio] == elemento) {
            System.out.println("Encontrado no índice " +
                meio + " após " + comparacoes + 
                " comparações");
            return meio;
        }
        if (arr[meio] < elemento) {
            inicio = meio + 1;
        } else {
            fim = meio - 1;
        }
    }
    System.out.println("Elemento não encontrado" +
        " após " + comparacoes + " comparações");

    long depois = System.nanoTime();
    double tempo = (depois - antes) / 1_000_000.0;
    System.out.printf("Tempo: %.3f ms \n", tempo);

    return -1;
}