package ru.nsu.fit.oop.Task_1_2_1;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class MyStack<E> implements Collection<E> {

    private E[] stack;
    private int currentSize;

    @SuppressWarnings("unchecked")
    public MyStack(Class<E> elemClass, int initialCapacity) {
        stack = (E[]) Array.newInstance(elemClass, initialCapacity);
        currentSize = 0;
    }

    public MyStack(Class<E> elemClass) {
        this(elemClass, 1000);
    }

    public MyStack(Class<E> elemClass, Collection<? extends E> collection) {
        this(elemClass);
        addAll(collection);
    }

    public void push(E elem) {
        stack[currentSize++] = elem;
    }

    public E pop() {
        if (isEmpty()) throw new IndexOutOfBoundsException("Stack is empty");

        E poppedElem = stack[--currentSize];
        stack[currentSize] = null;

        return poppedElem;
    }

    public void pushStack(MyStack<E> pushStack) {
        for (E pushElem : pushStack) {
            push(pushElem);
        }
    }

    public MyStack<E> popStack(Class<E> elemClass, int popElemCount) {
        if (popElemCount > currentSize) throw new IndexOutOfBoundsException(popElemCount + " > " + currentSize);

        MyStack<E> popStack = new MyStack<>(elemClass);
        for (int i = currentSize - popElemCount; i < currentSize; i++) {
            popStack.push(stack[i]);
            stack[i] = null;
        }
        currentSize -= popElemCount;
        return popStack;
    }

    private void grow() {
        stack = Arrays.copyOf(stack, stack.length * 2);
    }

    @Override
    public int size() {
        return currentSize;
    }

    @Override
    public boolean isEmpty() {
        return currentSize == 0;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean contains(Object o) {
        E obj = (E) o;
        for (E elem : stack) {
            if (obj.equals(elem)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private int listIndex = 0;

            @Override
            public boolean hasNext() {
                return listIndex != currentSize;
            }

            @Override
            public E next() {
                return stack[listIndex++];
            }
        };
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(stack, currentSize);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        if (a.length < currentSize)
            return (T[]) Arrays.copyOf(stack, currentSize, a.getClass());

        System.arraycopy(stack, 0, a, 0, currentSize);

        if (a.length > currentSize)
            stack[currentSize] = null;

        return a;
    }

    @Override
    public boolean add(E e) {
        if (currentSize == stack.length)
            grow();
        stack[currentSize++] = e;

        return true;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean remove(Object o) {
        if (o == null) {
            return false;
        }

        E obj = (E) o;
        for (int i = 0; i < currentSize; i++) {
            if (stack[i].equals(obj)) {
                System.arraycopy(stack, i + 1, stack, i, currentSize - i - 1);
                stack[currentSize--] = null;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object elem : c) {
            if (!contains(elem)) return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        for (E elem : c) {
            add(elem);
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean isChanged = false;
        for (Object elem : c) {
            if (remove(elem)) isChanged = true;
        }
        return isChanged;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean isChanged = false;
        for (E elem : stack) {
            if (!c.contains(elem))
                if (remove(elem)) isChanged = true;
        }
        return isChanged;
    }

    @Override
    public void clear() {
        for (int i = 0; i < currentSize; i++) {
            stack[i] = null;
        }
        currentSize = 0;
    }
}
