import java.util.Scanner;

void main() {
    Scanner scanner = new Scanner(System.in);

    // ENTRADA
    System.out.println("Digite a quantidade de notas:");
    int quantidadeNotas = scanner.nextInt();
    double somaNotas = 0.0;

    for (int indice = 0; indice < quantidadeNotas; indice++) {
        int numeroProva = indice + 1;
        System.out.println("Digite a nota da prova " + 
            numeroProva + ":" );
        double nota = scanner.nextDouble();
        somaNotas += nota;
    }

    // PROCESSAMENTO
    double media = somaNotas / quantidadeNotas;
    String situacao;

    if (media >= 7.0) {
        situacao = "Aprovação direta!";
    } else if (media >= 4.0) {
        situacao = "Em exame";
    } else {
        situacao = "Reprovação direta";
    }

    // SAÍDA
    System.out.println("Média: " + media);
    System.out.println("Situação: " + situacao);
}