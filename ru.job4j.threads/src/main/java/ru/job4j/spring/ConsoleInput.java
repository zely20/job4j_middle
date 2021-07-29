package ru.job4j.spring;

import java.util.Scanner;

public class ConsoleInput {

    private Scanner scanner = new Scanner(System.in);

    public String askString(String text) {
        System.out.println(text);
        return scanner.nextLine();
    }

    public int askInt(String text) {
        return Integer.parseInt(askString(text));
    }
}
