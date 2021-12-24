import java.lang.reflect.Array;
import java.util.*;

public class MyTree<E> implements Collection<E> {

    private Node<E> root;
    private int size = 0;

    public MyTree(E data) {
        this.root = new Node<>(data);
        this.size = 1;
    }

    public MyTree() {
    }

    public Node<E> getRoot() {
        return root;
    }

    public static class Node<E> {
        private final E data;
        private Node<E> ancestor;
        private final List<Node<E>> descendants = new ArrayList<>();

        private Node(E data) {
            this.data = data;
        }

        public E getData() {
            return data;
        }
    }

    public Node<E> addElem(Node<E> ancestor, E elem) {
        Node<E> node = new Node<>(elem);
        node.ancestor = ancestor;
        if (root == null) {
            if (ancestor == null) {
                root = node;
            } else {
                throw new NoSuchElementException("Given node is not in a tree");
            }
        } else {
            ancestor.descendants.add(node);
        }

        size++;
        return node;
    }

    public Node<E> addElem(E elem) {
        Node<E> node = new Node<>(elem);
        node.ancestor = root;
        if (root == null) {
            root = node;
        } else {
            root.descendants.add(node);
        }

        size++;
        return node;
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

            private final Stack<Node<E>> stack = new Stack<>();

            {
                stack.push(root);
            }

            @Override
            public boolean hasNext() {
                return !stack.isEmpty();
            }

            @Override
            public E next() {
                if (!stack.isEmpty()) {
                    Node<E> curNode = stack.pop();
                    for (int i = curNode.descendants.size() - 1; i >= 0; i--) {
                        stack.push(curNode.descendants.get(i));
                    }
                    return curNode.data;
                } else {
                    throw new NoSuchElementException("There are no more elements of a tree");
                }
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
    @SuppressWarnings("unchecked")
    public boolean remove(Object o) {
        E obj = (E) o;
        // if we need to remove the root node
        // we will make the leftmost descendent of root the new root
        if (root.data.equals(obj)) {
            if (root.descendants.size() != 0) {
                for (int i = 1; i < root.descendants.size(); i++) {
                    root.descendants.get(0).descendants.add(root.descendants.get(i));
                }
                root.descendants.get(0).ancestor = null;
                root = root.descendants.get(0);
                size--;
            } else {
                root = null;
                size = 0;
            }
            return true;
        }

        Node<E> curNode = root;
        for (int i = 0; i < size;) {
            for (int k = 0; k < curNode.descendants.size(); k++) {
                Node<E> descendant = curNode.descendants.get(k);
                if (descendant.data.equals(obj)) {
                    curNode.descendants.addAll(k, descendant.descendants);
                    descendant.descendants.forEach(eNode -> eNode.ancestor = curNode);
                    curNode.descendants.remove(k);
                    return true;
                }
                i++;
            }
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!contains(o)) return false;
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        for (E e : c) {
            add(e);
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean isChanged = false;
        for (Object o : c) {
            if (remove(o)) isChanged = true;
        }
        return isChanged;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean isChanged = false;
        for (E e : this) {
            if (!c.contains(e)) {
                remove(e);
                isChanged = true;
            }
        }
        return isChanged;
    }

    @Override
    public void clear() {
        // garbage collector will do all the work
        root = null;
        size = 0;
    }
}
