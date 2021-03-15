package ru.job4j.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotification {

    ExecutorService pool = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors());

    public void emailTo(UserEmail user) {
        String subject = "Notification " + user.getName() + " to email " + user.getEmail() +".";
        String body = "Add a new event to " + user.getName();
        pool.submit(new Runnable() {
            @Override
            public void run() {
                send(subject, body, user.getEmail());
            }
        });
    }

    public void close(){
        pool.shutdown();
    }

    public void send(String subject, String body, String email){}
}
class UserEmail {
    private String name;
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
