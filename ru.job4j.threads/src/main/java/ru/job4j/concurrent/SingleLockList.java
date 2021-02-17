package ru.job4j.concurrent;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Iterator;
import java.util.Objects;

@ThreadSafe
public class SingleLockList<T> implements Iterable<T> {
    @GuardedBy("this")
    private volatile SimpleArray<T> list = new SimpleArray<>();

    public void add(T value) {
        list.add(value);
    }

    public T get(int index) {
        return list.get(index);
    }

    private SimpleArray<T> copy(SimpleArray<T> inList){
        SimpleArray<T> temp = new SimpleArray<>();
        for (T ob : inList) {
            temp.add(ob);
        }
        return temp;
    }

    @Override
    public Iterator<T> iterator() {
        return copy(this.list).iterator();
    }
}
