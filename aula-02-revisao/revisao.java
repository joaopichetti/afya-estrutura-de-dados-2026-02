import java.util.Scanner;

void main() {
    Scanner scanner = new Scanner(System.in);

    // ENTRADA
    System.out.println("Digite a quantidade de notas:");
    int quantidadeNotas = scanner.nextInt();
    double somaNotas = 0.0;

    for (int indice = 0; indice < quantidadeNotas; indice++) {
        int numeroProva = indice + 1;
        double nota = 0.0;
        boolean notaValida = false;
        do {
            System.out.println("Digite a nota da prova " + 
                numeroProva + " (0 a 10):" );
            nota = scanner.nextDouble();
            notaValida = (nota >= 0) && (nota <= 10);
            if (!notaValida) {
                System.out.println("Você deve informar um valor" +
                    " entre 0 e 10");
            }
        } while (!notaValida);
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