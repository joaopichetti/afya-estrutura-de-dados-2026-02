void main() {
    int[] array1 = { 10 };
    int[] array2 = new Random().ints(1_000, 1, 101).toArray();
    int[] array3 = new Random().ints(100_000, 1, 101).toArray();
    int[] array4 = new Random().ints(1_000_000, 1, 1001).toArray();
    imprimirPrimeiroRegistro(array1);
    imprimirPrimeiroRegistro(array2);
    imprimirPrimeiroRegistro(array3);
    imprimirPrimeiroRegistro(array4);
}

// Exemplo Complexidade Constante O(1)
void imprimirPrimeiroRegistro(int[] arr) {
    System.out.println("Imprimindo primeiro registro de um array com tamanho " + arr.length);
    long antes = System.nanoTime();
    System.out.println(arr[0]);
    long depois = System.nanoTime();
    double tempo = (depois - antes) / 1_000_000.0;
    System.out.printf("Tempo: %.3f ms \n", tempo);
}