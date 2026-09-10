void main() {
    int[] array1 = { 10 };
    int[] array2 = new Random().ints(10, 1, 101).toArray();
    int[] array3 = new Random().ints(1_000, 1, 101).toArray();
    int[] array4 = new Random().ints(10_000, 1, 1001).toArray();
    algoritmoLinearitmico(array1);
    algoritmoLinearitmico(array2);
    algoritmoLinearitmico(array3);
    algoritmoLinearitmico(array4);
}

// Exemplo Complexidade Linearítmica O(n log n)
void algoritmoLinearitmico(int[] arr) {
    long antes = System.nanoTime();

    int n = arr.length;
    long operacoes = 0;
    // loop externo: roda N vezes -> O(n)
    for (int i = 0; i < n; i++) {
        // loop interno: duplica o passo -> O(log n)
        for (int j = 1; j < n; j *= 2) {
            operacoes++;
        }
    }
    System.out.printf("Tamanho (n): %d \n", n);
    System.out.printf("Operações: %d \n", operacoes);

    long depois = System.nanoTime();
    double tempo = (depois - antes) / 1_000_000.0;
    System.out.printf("Tempo: %.3f ms \n", tempo);
}