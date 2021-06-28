package ru.job4j.hibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Table(name = "mark_cars")
public class MarkCar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Model> models = new ArrayList<>();

    public MarkCar() {
    }

    public static MarkCar of(String name) {
        MarkCar markCar = new MarkCar();
        markCar.name = name;
        return markCar;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setModels(Model model){
        this.models.add(model);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MarkCar markCar = (MarkCar) o;
        return Objects.equals(id, markCar.id) &&
                Objects.equals(name, markCar.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "MarkCar{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
