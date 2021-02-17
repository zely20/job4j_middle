package ru.job4j.concurrent;

import net.jcip.annotations.ThreadSafe;

import java.util.HashSet;
import java.util.Set;

@ThreadSafe
public class UserStorage {

    Set<User2> users = new HashSet<>();

    public synchronized boolean add (User2 user){
        if(user != null){
            users.add(user);
            return true;
        }
        return false;
    }
    public synchronized boolean update(User2 user){
        if(users.contains(user)){
            users.add(user);
            return true;
        }
        return false;
    }
    public synchronized boolean delete(User2 user){
        if (users.contains(user)) {
            users.remove(user);
            return true;
        }
        return false;
    }
    public synchronized void transfer(int fromId, int toId, int amount){
        User2 userTempFirst = users.stream()
                .filter(user -> user.getId() == fromId)
                .findFirst()
                .get();
        User2 userTempSecond = users.stream()
                .filter(user -> user.getId() == toId)
                .findFirst()
                .get();
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
