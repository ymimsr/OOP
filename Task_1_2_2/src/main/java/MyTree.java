import java.lang.reflect.Array;
import java.util.*;

public class MyTree<E> implements Collection<E> {

    private E data;
    private MyTree<E> ancestor;
    private List<MyTree<E>> descendants;
    private int size;

    public MyTree(E data) {
        this.data = data;
        this.ancestor = null;
        size = 1;
    }

    public MyTree<E> addElem(E elem, MyTree<E> node) {
        MyTree<E> newNode = new MyTree<>(elem);
        newNode.ancestor = node;
        node.descendants.add(newNode);
        size++;

        return newNode;
    }

    public MyTree<E> addElem(E elem) {
        MyTree<E> newNode = new MyTree<>(elem);
        newNode.ancestor = this;
        descendants.add(newNode);
        size++;

        return newNode;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean contains(Object o) {
        E obj = (E) o;
        for (E elem : this) {
            if (obj.equals(elem)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            // index is depth of the tree, the depth <= size of tree
            // value is index in the descendents list
            //private int[] descIndex = new int[size];
            private int iterIndex = 0;
            private int descIndex = 0;

            @Override
            public boolean hasNext() {
                return iterIndex < size;
            }

            @Override
            public E next() {
                if (descendants.size() != 0) {
                    return descendants.get(descIndex++).iterator().next();
                } else {
                    iterIndex++;
                    return data;
                }
            }

        };
    }

    public ListIterator<E> listIterator() {
        return new ListIterator<>() {

            private int iterIndex = 0;
            private int descIndex = 0;

            @Override
            public boolean hasNext() {
                return iterIndex < size;
            }

            @Override
            public E next() {
                if (descendants.size() != 0) {
                    return descendants.get(descIndex++).iterator().next();
                } else {
                    iterIndex++;
                    return data;
                }
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }

            @Override
            public E previous() {
                return null;
            }

            @Override
            public int nextIndex() {
                return 0;
            }

            @Override
            public int previousIndex() {
                return 0;
            }

            @Override
            public void remove() {
                if (MyTree.this.ancestor != null) {
                    MyTree.this.ancestor.descendants.remove(descIndex);
                }
            }

            @Override
            public void set(E e) {

            }

            @Override
            public void add(E e) {

            }

        };
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        int i = 0;
        for (E elem : this) {
            array[i++] = elem;
        }

        return array;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            T[] eArray = (T[]) Array.newInstance(a.getClass(), size);
            int i = 0;
            for (E elem : this) {
                eArray[i++] = (T) elem;
            }

            return eArray;
        } else {
            int i = 0;
            for (E elem : this) {
                a[i++] = (T) elem;
            }

            return a;
        }
    }

    @Override
    public boolean add(E e) {
        addElem(e);
        return true;
    }

    @Override
    public boolean remove(Object o) {

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        // garbage collector will do all the work
        descendants = null;
    }
}
