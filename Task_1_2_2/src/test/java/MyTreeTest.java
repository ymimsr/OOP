import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MyTreeTest {

    private final static MyTree<String> tree = new MyTree<>();


    //             0
    //    1        6        10
    //  2   5    7   8      11
    // 3 4           9    12  13
    @BeforeEach
    public void init() {
        // root
        tree.clear();
        tree.add("0");
        MyTree.Node<String> node1 = tree.addElem("1");
        MyTree.Node<String> node6 = tree.addElem("6");
        MyTree.Node<String> node10 = tree.addElem("10");
        MyTree.Node<String> node2 = tree.addElem(node1, "2");
        tree.addElem(node2, "3");
        tree.addElem(node2, "4");
        tree.addElem(node1, "5");
        tree.addElem(node6, "7");
        MyTree.Node<String> node8 = tree.addElem(node6, "8");
        tree.addElem(node8, "9");
        MyTree.Node<String> node11 = tree.addElem(node10, "11");
        tree.addElem(node11, "12");
        tree.addElem(node11, "13");
    }


    @Test
    public void iteratorTest() {
        String result = tree.stream().reduce((a, b) -> a + b).orElse("0");

        assertEquals("012345678910111213", result);
    }

    @Test
    public void streamApiTest() {
        String result = tree.stream()
                .filter(node -> node.contains("1"))
                .reduce((a, b) -> a + b)
                .orElse("0");

        assertEquals("110111213", result);
    }

    @Test
    public void removeTest() {
        tree.remove("10");
        tree.remove("11");
        tree.remove("12");
        tree.remove("13");

        String result = tree.stream()
                .reduce((a, b) -> a + b).orElse("0");

        assertEquals("0123456789", result);
    }

    @Test
    public void removeUntilEmptyTest() {
        int i = 0;
        while (tree.remove(i + "")) {
            i++;
        }

        assertTrue(tree.isEmpty());
    }
}
