import java.util.Scanner;

void main() {
    Scanner scanner = new Scanner(System.in);

    // ENTRADA
    System.out.println("Digite a quantidade de notas:");
    int quantidadeNotas = scanner.nextInt();
    double somaNotas = 0.0;

    for (int indice = 0; indice < quantidadeNotas; indice++) {
        int numeroProva = indice + 1;
        String mensagem = "Digite a nota da prova " + 
                numeroProva + " (0 a 10):";
        double nota = lerDadoValido(
            scanner, mensagem, 0, 10);
        somaNotas += nota;
    }

    String mensagem = "Digite a frequência em % " +
            "(0 a 100)";
    double frequencia = lerDadoValido(
        scanner, mensagem, 0, 100);

    // PROCESSAMENTO
    double media = somaNotas / quantidadeNotas;
    String situacao = verificarSituacao(
        media, frequencia);

    // SAÍDA
    System.out.println("Média: " + media);
    System.out.println("Situação: " + situacao);
}

double lerDadoValido(
    Scanner scanner,
    String mensagem,
    double min,
    double max
) {
    double valor = 0.0;
    boolean valorValido = false;
    do {
        System.out.println(mensagem);
        valor = scanner.nextDouble();
        valorValido = (valor >= min) && 
            (valor <= max);
        if (!valorValido) {
            System.out.println(
                "Você deve informar um valor" +
                " entre " + min + " e " + max);
        }
    } while (!valorValido);
    return valor;
}

String verificarSituacao(
    double media, 
    double frequencia
) {
    if (frequencia < 75.0) {
        return "Reprovação por frequência";
    } else if (media >= 7.0) {
        return "Aprovação direta!";
    } else if (media >= 4.0) {
        return "Em exame";
    } else {
        return "Reprovação por nota";
    }
}