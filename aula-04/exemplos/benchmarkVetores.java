void main() {
    final int TOTAL_ITENS = 200_000;

    // Medição da Estratégia Linear
    long inicio = System.currentTimeMillis();
    VetorDinamicoDouble vetorLinear = new VetorDinamicoDouble(10, VetorDinamicoDouble.ESTRATEGIA_LINEAR);
    for (int i = 0; i < TOTAL_ITENS; i++) {
        vetorLinear.adicionar(i);
    }
    long fim = System.currentTimeMillis();
    long diferencaTempo = fim - inicio;
    System.out.println("Tempo com Estragégia Linear: " + diferencaTempo + " ms");

    // Medição da Estratégia Geométrica
    inicio = System.currentTimeMillis();
    VetorDinamicoDouble vetorGeometrico = new VetorDinamicoDouble();
    for (int i = 0; i < TOTAL_ITENS; i++) {
        vetorGeometrico.adicionar(i);
    }
    fim = System.currentTimeMillis();
    diferencaTempo = fim - inicio;
    System.out.println("Tempo com Estragégia Geométrica: " + diferencaTempo + " ms");

    // Medição com Pré-Alocação
    inicio = System.currentTimeMillis();
    VetorDinamicoDouble vetorPreAlocado = new VetorDinamicoDouble(TOTAL_ITENS, VetorDinamicoDouble.ESTRATEGIA_GEOMETRICA);
    for (int i = 0; i < TOTAL_ITENS; i++) {
        vetorPreAlocado.adicionar(i);
    }
    fim = System.currentTimeMillis();
    diferencaTempo = fim - inicio;
    System.out.println("Tempo com Pré-Alocação: " + diferencaTempo + " ms");
}