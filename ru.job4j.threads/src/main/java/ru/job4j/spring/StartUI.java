package ru.job4j.spring;

import org.springframework.stereotype.Component;

@Component
public class StartUI {

    private Store store;
    private ConsoleInput consoleInput;

    public StartUI(Store store, ConsoleInput consoleInput) {
        this.store = store;
        this.consoleInput = consoleInput;
    }

    public void add(String value) {
        store.add(value);
    }

    public void print() {
        for (String value : store.getAll()) {
            System.out.println(value);
        }
    }

    public String enterText() {
        return consoleInput.askString("Enter text: ");
    }

    public int enterNumber() {
        return consoleInput.askInt("Enter number: ");
    }
}