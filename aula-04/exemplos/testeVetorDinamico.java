void main() {
    VetorDinamicoDouble vetorDinamico = new VetorDinamicoDouble();
    System.out.println("Quantidade de registros: " + 
        vetorDinamico.quantidadeRegistros());
    System.out.println("Capacidade: " + 
        vetorDinamico.capacidade());
    for (int i = 0; i < 15; i++) {
        vetorDinamico.adicionar(i * 2);
    }
    System.out.println("Quantidade de registros: " + 
        vetorDinamico.quantidadeRegistros());
    System.out.println("Capacidade: " + 
        vetorDinamico.capacidade());
    for (int i = 0; i < vetorDinamico.quantidadeRegistros(); i++) {
        double valor = vetorDinamico.obter(i);
        System.out.println("Valor na posição " + i + ": " + valor);        
    }
    for (int i = 14; i > 8; i--) {
        vetorDinamico.remover(i);
    }
    System.out.println("Quantidade de registros: " + 
        vetorDinamico.quantidadeRegistros());
    System.out.println("Capacidade: " + 
        vetorDinamico.capacidade());
    for (int i = 0; i < vetorDinamico.quantidadeRegistros(); i++) {
        double valor = vetorDinamico.obter(i);
        System.out.println("Valor na posição " + i + ": " + valor);        
    }
}