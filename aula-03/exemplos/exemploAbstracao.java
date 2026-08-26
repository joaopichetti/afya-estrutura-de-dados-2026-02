import java.util.Collection;
import java.util.Iterator;

void main() {
    ArrayList<String> nomes = new ArrayList<>();
    nomes.add("João");
    nomes.add("Carlos");
    nomes.add("Cezar");
    nomes.add("João");
    percorrerColecao(nomes);

    HashSet<String> cores = new HashSet<>();
    cores.add("Azul");
    cores.add("Verde");
    cores.add("Amarelo");
    cores.add("Branca");
    cores.add("azul");
    percorrerColecao(cores);

    LinkedList<Integer> idades = new LinkedList<>();
    percorrerColecao(idades);

    MinhaCollection minhaCollection = new MinhaCollection();
    percorrerColecao(minhaCollection);
}

void percorrerColecao(Collection colecao) {
    System.out.println("Tamanho da coleção: " +
        colecao.size());
    System.out.println("Coleção está vazia? " +
        colecao.isEmpty());
    Iterator iterator = colecao.iterator();
    while (iterator.hasNext()) {
        var objeto = iterator.next();
        System.out.println("Objeto: "+ objeto);
    }
}

class MinhaCollection implements Collection {

    @Override
    public int size() {
        return 34;
    }

    @Override
    public boolean isEmpty() {
        return size() <= 0;
    }

    @Override
    public boolean contains(Object o) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'contains'");
    }

    @Override
    public Iterator iterator() {
        return Collections.emptyIterator();
    }

    @Override
    public Object[] toArray() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toArray'");
    }

    @Override
    public Object[] toArray(Object[] a) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toArray'");
    }

    @Override
    public boolean add(Object e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'add'");
    }

    @Override
    public boolean remove(Object o) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }

    @Override
    public boolean containsAll(Collection c) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'containsAll'");
    }

    @Override
    public boolean addAll(Collection c) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addAll'");
    }

    @Override
    public boolean removeAll(Collection c) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeAll'");
    }

    @Override
    public boolean retainAll(Collection c) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'retainAll'");
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'clear'");
    }
    
}
