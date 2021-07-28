package ru.job4j.hql;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

public class Main {

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
            System.out.println(query.uniqueResult());

            System.out.println("Find by name");
            query = session.createQuery("from Candidate c where c.name = :fName");
            query.setParameter("fName", "Alex");
            System.out.println(query.uniqueResult());
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
