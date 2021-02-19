package ru.job4j.concurrent;

import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public class UserStorage {

    final Map<Integer, User2> users = new HashMap<>();

    public synchronized boolean add (User2 user){
        if(user != null){
            users.put(user.getId(),user);
            return true;
        }
        return false;
    }
    public synchronized boolean update(User2 user){
        if(users.containsKey(user.getId())){
            users.put(user.getId(),user);
            return true;
        }
        return false;
    }
    public synchronized boolean delete(User2 user){
        if (users.containsKey(user.getId())) {
            users.remove(user.getId());
            return true;
        }
        return false;
    }
    public synchronized void transfer(int fromId, int toId, int amount){
        User2 userTempFirst = users.get(fromId);
        User2 userTempSecond = users.get(toId);
        userTempFirst.setAmount(userTempFirst.getAmount() - amount);
        userTempSecond.setAmount(userTempSecond.getAmount() + amount);
        update(userTempFirst);
        update(userTempSecond);
    }
}

class User2{
    private volatile int id;
    private volatile int amount;

    public User2(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
