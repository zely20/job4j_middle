package ru.job4j.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringDI {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("ru.job4j.spring");
        context.refresh();
        StartUI ui = context.getBean(StartUI.class);
        String text = ui.enterText();
        ui.add(text);
        ui.print();
    }
}
