package ru.job4j.concurrent;

import java.util.*;

public class SimpleArray<T> implements Iterable<T> {

    private Object[] container;
    private int pointer = 0;
    private int modCount = 0;

    public SimpleArray() {
        this.container = new Object[10];
    }

    public SimpleArray(int capacity) {
        this.container = new Object[capacity];
    }

    public int size() {
        return pointer;
    }

    public T get(int index) {
        Objects.checkIndex(index, pointer);
        return (T) container[index];
    }

    public void add(T model) {

        modCount++;
        if(pointer == container.length) {
            container = Arrays.copyOf(container, container.length + 10);
        }
        container[pointer++] = model;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int cursor = 0;
            int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if(expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return cursor < pointer;
            }

            @Override
            public T next() {
                if(!hasNext()){
                    throw new NoSuchElementException();
                }
                return (T) container[cursor++];
            }
        };
    }
}
