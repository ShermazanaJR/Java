import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.function.Consumer;

class IndexedTree<T> implements Iterable<T>{
    private Node<T> tree;

    public IndexedTree(Node<T> treee){
        this.tree = treee;
    }
    public int size(){
        return tree.size();
    }
    public T get(int i){
        return tree.get(i);
    }
    public void update(int i, T x){
        tree.update(i,x);
    }
    public void insert(int i,T x){
        tree.insert(i,x);
    }
    public void remove(int i){
        tree.remove(i);
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int index = 0;
            @Override
            public boolean hasNext() {
                return index < size();
            }

            @Override
            public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                return get(index++);
            }
        };
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator<T> spliterator() {
        return Iterable.super.spliterator();
    }
}