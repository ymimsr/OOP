package ru.nsu.fit.oop.Task_1_2_1;

import java.util.Collection;
import java.util.Vector;

public class MyStack<E> extends Vector<E> {

    public MyStack(int initialCapacity, int capacityIncrement) {
        super(initialCapacity, capacityIncrement);
    }

    public MyStack(int initialCapacity) {
        super(initialCapacity, 0);
    }

    public MyStack() {
        super();
    }

    public MyStack(Collection<? extends E> c) {
        super(c);
    }

    /**
     * Pushes element to the top of the stack
     *
     * @param elem - element to be pushed
     */
    public synchronized void push(E elem) {
        addElement(elem);
    }

    /**
     * Pops element from the top of the stack
     *
     * @return top element in the stack
     */
    public synchronized E pop() {
        E elem = elementAt(elementCount - 1);
        removeElementAt(elementCount - 1);

        return elem;
    }

    /**
     * Pushes all elements from given stack to this stack
     *
     * @param stack to be pushed on top of this stack
     */
    public synchronized void pushStack(MyStack<? extends E> stack) {
        while (!stack.isEmpty()) {
            this.push(stack.pop());
        }
    }

    /**
     * Pops elements from this stack and returns them as a stack
     *
     * @param elements how much elements to be popped
     * @return stack of popped elements
     */
    public synchronized MyStack<E> popStack(int elements) {
        if (elements > elementCount)
            throw new ArrayIndexOutOfBoundsException(elements + " >= " + elementCount);

        MyStack<E> stack = new MyStack<>();
        for (int i = 0; i < elements; i++) {
            stack.push(this.pop());
        }

        return stack;
    }

    /**
     * Returns the current capacity of a stack
     *
     * @return the current capacity of a stack
     */
    public synchronized int count() {
        return elementCount;
    }
}
