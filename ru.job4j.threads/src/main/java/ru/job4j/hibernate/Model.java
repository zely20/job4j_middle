package ru.job4j.hibernate;

import javax.persistence.*;
import java.util.Objects;

@Table(name = "models")
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    public static Model of(String name) {
        Model role = new Model();
        role.name = name;
        return role;
    }

    public Model() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Model model = (Model) o;
        return Objects.equals(id, model.id) &&
                Objects.equals(name, model.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Model{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
