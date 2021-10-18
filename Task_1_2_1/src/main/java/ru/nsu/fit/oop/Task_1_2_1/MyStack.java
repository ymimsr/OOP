package ru.nsu.fit.oop.Task_1_2_1;

import java.util.Collection;
import java.util.Stack;
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

    public synchronized void push(E elem) {
        addElement(elem);
    }

    public synchronized E pop() {
        E elem = elementAt(elementCount - 1);
        removeElementAt(elementCount - 1);

        return elem;
    }

    public synchronized void pushStack(MyStack<? extends E> stack) {
        while (!stack.isEmpty()) {
            this.push(stack.pop());
        }
    }

    public synchronized MyStack<E> popStack(int elements) {
        if (elements > elementCount)
            throw new ArrayIndexOutOfBoundsException(elements + " >= " + elementCount);

        MyStack<E> stack = new MyStack<>();
        for (int i = 0; i < elements; i++) {
            stack.push(this.pop());
        }

        return stack;
    }

    public synchronized int count() {
        return elementCount;
    }
}
