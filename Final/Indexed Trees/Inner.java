import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.function.Consumer;

interface Node<T> {
    int size();

    T get(int i);

    void update(int i, T x);

    Node<T> insert(int i, T x);

    Node<T> remove(int i);
}

class Leaf<T> implements Node<T>{
    T value;

    public Leaf(T x) {
        value = x;
    }

    public int size() {
        return 1;
    }

    public T get(int i) {
        if (i == 0) {
            return value;
        } else {
            throw new IndexOutOfBoundsException("index out of bounds for get");
        }
    }

    public void update(int i, T x) {
        if (i == 0) {
            value = x;
        } else {
            throw new IndexOutOfBoundsException("index out of bounds for update");

        }
    }
    public Inner<T> insert(int i, T x){
        if (i <= 0) {
            return new Inner<T>(new Leaf<T>(x), this,1,1);
        } else {
            return new Inner<T>(this, new Leaf<T>(x),1,1);
        }
    }
    public Node<T> remove(int i){
        if(i==0){
            return null;
        }
        else {
            throw new IndexOutOfBoundsException("index out of bounds for remove");
        }
    }


}

class Inner<T> implements Node<T> {
    Node<T> left;
    Node<T> right;
    int lsize;
    int rsize;

    public Inner(Node<T> left, Node<T> right, int lsize, int rsize) {
        this.left = left;
        this.right = right;
        this.lsize = lsize;
        this.rsize = rsize;
    }
    public int size(){
        return lsize+rsize;
    }
    public T get(int i){
        if (i-lsize<0){
            return left.get(i);
        }
        else{
            return right.get(i-lsize);
        }
    }

    public void update(int i, T x){
        if(i-lsize<0){
            left.update(i,x);
        }
        else {
            right.update(i-lsize,x);
        }
    }
    public Node<T> insert(int i, T x){
        if (i<0){
            return left.insert(0,x);
        }
        else if (0<=i && i<=size()){
            if(i-lsize<0){
                return left.insert(i,x);
            }else {
                return right.insert(i-lsize,x);
            }
        }else {
            throw new IndexOutOfBoundsException("index out of bounds for insert");
        }
    }

    @Override
    public Node<T> remove(int i) {
        if(i-lsize<0){
            right.remove(i);
        }else {
            right.remove(i-lsize);
        }
        return this;
    }
}
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