public class Node {
    int dado;
    Node proximo;

    public Node(int dado) {
        this.dado = dado;
    }

    public Node(int dado, Node proximo) {
        this.dado = dado;
        this.proximo = proximo;
    }
}
