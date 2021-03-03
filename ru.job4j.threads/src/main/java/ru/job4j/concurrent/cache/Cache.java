package ru.job4j.concurrent.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Cache {
    private final Map<Integer, Base> memory = new ConcurrentHashMap<>();

    public boolean add(Base model) {
        return memory.putIfAbsent(model.getId(), model) == null;
    }

    public boolean update(Base model) {
        if(checkVersion(model)) {
           return memory.replace(model.getId(), model) != null;
        }
        return false;
    }

    public void delete(Base model) {
        if(checkVersion(model)) {
            memory.remove(model.getId(), model);
        }
    }

    private boolean checkVersion(Base model) {
        Base stored = memory.get(model.getId());
        if (stored.getVersion() != model.getVersion()) {
            throw new OptimisticException("Versions are not equal");
        }
        return true;
    }
}
