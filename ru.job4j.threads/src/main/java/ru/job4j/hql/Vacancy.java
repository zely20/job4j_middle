package ru.job4j.hql;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "vacancy")
public class Vacancy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    public static Vacancy of(String name) {
        Vacancy vacancy = new Vacancy();
        vacancy.name = name;
        return vacancy;
    }
}
