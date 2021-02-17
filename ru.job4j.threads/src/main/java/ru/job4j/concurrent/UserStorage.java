package ru.job4j.concurrent;

import net.jcip.annotations.ThreadSafe;

import java.util.HashSet;
import java.util.Set;

@ThreadSafe
public class UserStorage {

    Set<User> users = new HashSet<>();

    public synchronized boolean add (User2 user){
        if(user != null){
            users.add(user);
        }
        return false;
    }
    public synchronized boolean update(User2 user){
        return false;
    }
    public synchronized boolean delete(User2 user){
        return false;
    }
    public synchronized void transfer(int fromId, int toId, int amount){

    }
}

class User2{
    private volatile int id;
    private volatile int amount;

    public User2(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }
}