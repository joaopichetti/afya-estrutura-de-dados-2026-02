import java.util.Scanner;

void main() {
    Scanner scanner = new Scanner(System.in);

    // ENTRADA
    System.out.println("Digite a primeira nota:");
    double nota1 = scanner.nextDouble();
    System.out.println("Digite a segunda nota:");
    double nota2 = scanner.nextDouble();

    // PROCESSAMENTO
    double media = (nota1 + nota2) / 2.0;

    // SAÍDA
    System.out.println("Média: " + media);
}