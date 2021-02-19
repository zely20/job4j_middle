package ru.job4j.concurrent;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Iterator;
import java.util.Objects;

@ThreadSafe
public class SingleLockList<T> implements Iterable<T> {
    @GuardedBy("this")
    private final SimpleArray<T> list = new SimpleArray<>();

    public synchronized void add(T value) {
        list.add(value);
    }

    public synchronized T get(int index) {
        return list.get(index);
    }

    private synchronized SimpleArray<T> copy(SimpleArray<T> inList){
        SimpleArray<T> temp = new SimpleArray<>();
        for (T ob : inList) {
            temp.add(ob);
        }
        return temp;
    }

    @Override
    public synchronized Iterator<T> iterator() {
        return copy(this.list).iterator();
    }
}
