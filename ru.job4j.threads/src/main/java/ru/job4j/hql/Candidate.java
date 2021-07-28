package ru.job4j.hql;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "candidates")
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String experience;
    private Integer salary;

    public static Candidate of( String name, String experience, Integer salary) {
        Candidate candidate = new Candidate();
        candidate.name = name;
        candidate.experience = experience;
        candidate.salary = salary;
        return candidate;
    }
}

class Main2 {

    public static void main(String[] args) {

        BaseOfVacations result = null;
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();
            Candidate one = Candidate.of("Alex", "Java", 300);
            BaseOfVacations base = BaseOfVacations.of("DB");
            Vacancy vOne = Vacancy.of("PHP");
            Vacancy vTwo = Vacancy.of("Java");
            base.setCandidate(one);
            base.setVacancies(List.of(vOne, vTwo));
            session.save(base);
            result = (BaseOfVacations) session.createQuery("select distinct b from BaseOfVacations b " +
                    "join fetch b.candidate c " +
                    "join fetch b.vacancies").uniqueResult();
            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
        System.out.println(result);
    }
}
