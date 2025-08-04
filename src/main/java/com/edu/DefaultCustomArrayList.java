package com.edu;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DefaultCustomArrayList<E> implements CustomArrayList<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size;

    public DefaultCustomArrayList() {
        this.elements = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    @Override
    public boolean add(E element) {
        ensureCapacity();
        elements[size++] = element;
        return true;
    }

    private void ensureCapacity() {
        if (size == elements.length) {
            int newCapacity = elements.length * 2;
            elements = java.util.Arrays.copyOf(elements, newCapacity);
        }
    }

    @Override
    public boolean remove(E element) {
        for (int i = 0; i < size; i++) {
            if (elements[i] == element || (element != null && element.equals(elements[i]))) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    private void remove(int index) {
        if (index >= 0 && index < size) {
            for (int i = index; i < size - 1; i++) {
                elements[i] = elements[i + 1];
            }
            elements[--size] = null;
        }
    }

    @Override
    public E get(int index) {
        if (index >= 0 && index < size) {
            return (E) elements[index];
        }
        throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    @Override
    public boolean contains(E element) {
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) {
                    return true;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(elements[i])) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int current = 0;

            @Override
            public boolean hasNext() {
                return current < size;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (E) elements[current++];
            }

            @Override
            public void remove() {
                if (current == 0) {
                    throw new IllegalStateException();
                }
                DefaultCustomArrayList.this.remove(--current);
            }
        };
    }
}