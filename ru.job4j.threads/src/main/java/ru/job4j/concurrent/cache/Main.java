package ru.job4j.concurrent.cache;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<Integer, Base> map = new HashMap<>();
        Base base = new Base(1, 0);
        Cache cache = new Cache();
        map.put(base.getId(), base);

        Base user1 = map.get(base.getId());
        user1.setName("User 1");

        cache.add(user1);

    }
}
