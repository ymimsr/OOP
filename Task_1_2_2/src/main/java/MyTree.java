import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class MyTree<E> implements Collection<E> {

    private Node root = null;
    private int treeSize = 0;

    private class Node {

        private final E value;
        private final Node ancestor;
        private final List<Node> descendants = new ArrayList<>();

        public Node(E value, Node ancestor) {
            this.value = value;
            this.ancestor = ancestor;
        }

        public E getValue() {
            return value;
        }

        public List<Node> getDescendants() {
            return descendants;
        }

        public Node getAncestor() {
            return ancestor;
        }

        public void addDescendant(Node desc) {
            descendants.add(desc);
        }

    }

    public MyTree(E root) {
        this.root = new Node(root, null);
        treeSize++;
    }

    public Node addNode(Node ancestor, E value) {
        Node node = new Node(value, ancestor);

        ancestor.addDescendant(node);
        treeSize++;

        return node;
    }

    public Node addNode(E value) {
        Node node = new Node(value, root);

        if (treeSize == 0) {
            root = node;
        } else {
            root.addDescendant(node);
        }

        return node;
    }



    @Override
    public int size() {
        return treeSize;
    }

    @Override
    public boolean isEmpty() {
        return treeSize == 0;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private int treeIndex = 0;
            private int anIndex = 0;
            private int descIndex = 0;
            private Node curNode = null;

            @Override
            public boolean hasNext() {
                return treeIndex < treeSize;
            }

            @Override
            public E next() {
                Node nextNode = null;

                if (treeIndex == 0) {
                    curNode = root;
                    nextNode = root;
                } else {
                    if (curNode.getDescendants().size() > descIndex) {
                        nextNode = curNode.getDescendants().get(descIndex++);
                    } else {
                        descIndex = 0;
                        anIndex++;
                        if (anIndex)
                    }
                }
                treeIndex++;

                return nextNode.value;
            }
        };
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(E e) {
        addNode(e);

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

    }
}
