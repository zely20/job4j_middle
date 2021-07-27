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

class Main {

    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            Candidate one = Candidate.of("Alex", "Java", 300);
            Candidate two = Candidate.of( "Vasya", "C++", 1000);
            Candidate three = Candidate.of( "Oleg", "JavaScript", 200);
            session.save(one);
            session.save(two);
            session.save(three);
            session.getTransaction().commit();
            Query query = session.createQuery("from Candidate");
            for(Object o : query.getResultList()){
                System.out.println(o);
            }
            System.out.println("=============");
            System.out.println("Find by ID");
            query = session.createQuery("from Candidate c where c.id = :fId");
            query.setParameter("fId", 1);
            System.out.println(query.getSingleResult());

            System.out.println("Find by name");
            query = session.createQuery("from Candidate c where c.name = :fName");
            query.setParameter("fName", "Alex");
            System.out.println(query.getSingleResult());
            System.out.println("=============");

            query = session.createQuery(
                    "update Candidate c set c.name = :newName  where c.id = :fId"
            );
            query.setParameter("newName", "Valodia");
            query.setParameter("fId", 1);
            query.executeUpdate();
            System.out.println("=============");
            System.out.println(session.createQuery("from Candidate c where c.id = 1").getSingleResult());
            System.out.println("=============");
            query = session.createQuery("delete from Candidate c where c.id = 1");
            query.executeUpdate();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
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
