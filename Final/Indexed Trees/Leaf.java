interface Node<T> {
    int size();

    T get(int i);

    void update(int i, T x);

    Node<T> insert(int i, T x);

    Node<T> remove(int i);
}

class Leaf<T>{
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

    public Inner<T> insert(int i, T x) {
        if (i <= 0) {
            return new Inner<T>(x, new Leaf<T>(x), this);
        } else {
            return new Inner<T>(value, this, new Leaf<T>(x));
        }
    }

    public T remove(int i) {
        if (i == 0) {
            return null;
        } else {
            throw new IndexOutOfBoundsException("index out of bounds for remove");
        }
    }

}