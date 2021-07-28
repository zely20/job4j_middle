package ru.job4j.hql;

import lombok.Data;
import ru.job4j.concurrent.cache.Base;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "base_vacations")
public class BaseOfVacations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @OneToOne(fetch = FetchType.LAZY)
    private Candidate candidate;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Vacancy> vacancies = new ArrayList<>();

    public static BaseOfVacations of(String name){
        BaseOfVacations base = new BaseOfVacations();
        base.name = name;
        return base;
    }
}
