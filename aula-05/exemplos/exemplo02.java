void main() {
    int[] array1 = { 10 };
    int[] array2 = new Random().ints(1_000, 1, 101).toArray();
    int[] array3 = new Random().ints(100_000, 1, 101).toArray();
    int[] array4 = new Random().ints(1_000_000, 1, 1001).toArray();
    somarValores(array1);
    somarValores(array2);
    somarValores(array3);
    somarValores(array4);
}

// Exemplo Complexidade Linear O(n)
void somarValores(int[] arr) {
    System.out.println("Somando valores de um array com tamanho " + arr.length);
    long antes = System.nanoTime();

    long soma = 0;
    for (int i = 0; i < arr.length; i++) {
        // soma = soma + arr[i];
        soma += arr[i];
    }
    System.out.println("Soma: " + soma);

    long depois = System.nanoTime();
    double tempo = (depois - antes) / 1_000_000.0;
    System.out.printf("Tempo: %.3f ms \n", tempo);
}