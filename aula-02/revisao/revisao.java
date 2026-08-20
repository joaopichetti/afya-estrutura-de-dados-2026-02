import java.util.Scanner;

void main() {
    Scanner scanner = new Scanner(System.in);

    // ENTRADA
    System.out.println("Digite a quantidade de notas:");
    int quantidadeNotas = scanner.nextInt();
    final int MAX_ALUNOS = 5;
    double[] frequencias = new double[MAX_ALUNOS];
    double[][] notas = new double[MAX_ALUNOS][quantidadeNotas];
    boolean[] cadastrados = new boolean[MAX_ALUNOS];

    int opcao;
    do {
        System.out.println("\n=== SISTEMA DE NOTAS E FREQUÊNCIA ===");
        System.out.println("1 - Cadastrar notas e frequência de um aluno");
        System.out.println("2 - Imprimir situação de um aluno específico");
        System.out.println("3 - Imprimir situação de todos os alunos");
        System.out.println("4 - Sair");
        String mensagem = "Escolha uma opção:";
        opcao = (int) lerDadoValido(scanner, mensagem, 1, 4);
        switch (opcao) {
            case 1 -> cadastrarAluno(scanner, frequencias, notas, 
                cadastrados, quantidadeNotas, MAX_ALUNOS);
            case 2 -> imprimirAlunoEspecifico(scanner, notas, 
                frequencias, cadastrados, MAX_ALUNOS);
            case 3 -> imprimirTodosAlunos(scanner, notas, frequencias, 
                cadastrados, MAX_ALUNOS);
            case 4 -> System.out.println("\nSaindo do sistema. Até logo!");
            default -> System.out.println("Opção inválida...");
        }
    } while (opcao != 4);
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

int lerId(Scanner scanner, int maxAlunos) {
    int maxId = maxAlunos - 1;
    String mensagem = "Digite o ID do aluno (0 a " + maxId + "): ";
    int id = (int) lerDadoValido(scanner, mensagem, 0, maxId);
    return id;
}

void cadastrarAluno(
    Scanner scanner,
    double[] frequencias,
    double[][] notas,
    boolean[] cadastrados,
    int quantidadeNotas,
    int maxAlunos
) {
    int id = lerId(scanner, maxAlunos);

    for (int indice = 0; indice < quantidadeNotas; indice++) {
        int numeroProva = indice + 1;
        String mensagem = "Digite a nota "+ numeroProva + ": ";
        double nota = lerDadoValido(scanner, mensagem, 0, 10);
        notas[id][indice] = nota;
    }

    String mensagem = "Difgite a frequência em % (0 a 100)";
    double frequencia = lerDadoValido(scanner, mensagem, 0, 100);
    frequencias[id] = frequencia;

    cadastrados[id] = true;

    System.out.println("Aluno ID " + id + " cadastrado com sucesso!");
}

double calcularMedia(double[] notas) {
    double somaNotas = 0.0;
    for (int indice = 0; indice < notas.length; indice++) {
        somaNotas += notas[indice];
    }
    return somaNotas / notas.length;
}

void exibirDadosAluno(int id, double[][] notas, double[] frequencias) {
    double media = calcularMedia(notas[id]);
    double frequencia = frequencias[id];
    String situacao = verificarSituacao(media, frequencia);

    System.out.printf(
        "Aluno ID %d | Média %.2f | Frequência %.1f%% | Situação %s%n",
        id, media, frequencia, situacao);
}

void imprimirAlunoEspecifico(
    Scanner scanner,
    double[][] notas,
    double[] frequencias,
    boolean[] cadastrados,
    int maxAlunos
) {
    int id = lerId(scanner, maxAlunos);
    if (!cadastrados[id]) {
        System.out.println("Aluno ID " + id + " ainda não foi cadastrado.");
        return;
    }
    exibirDadosAluno(id, notas, frequencias);
}

void imprimirTodosAlunos(
    Scanner scanner,
    double[][] notas,
    double[] frequencias,
    boolean[] cadastrados,
    int maxAlunos
) {
    boolean existeAlunoCadastrado = false;
    System.out.println("\n--- RELATÓRIO GERAL ---");

    for (int indice = 0; indice < maxAlunos; indice++) {
        if (cadastrados[indice]) {
            exibirDadosAluno(indice, notas, frequencias);
            existeAlunoCadastrado = true;
        }
    }

    if (!existeAlunoCadastrado) {
        System.out.println("Nenhum aluno cadastrado no sistema.");
    }
}