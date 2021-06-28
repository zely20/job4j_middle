package ru.job4j.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibRunBook {

    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        final SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        try (Session session = sf.openSession();) {
            session.beginTransaction();
            Book first = Book.of("first");
            Book second = Book.of("first");
            Book third = Book.of("first");

            Author author1 = Author.of("author1");
            Author author2 = Author.of("author2");
            Author author3 = Author.of("author3");
            author1.setBook(first);
            author2.setBook(first);
            author2.setBook(second);
            author3.setBook(first);
            author3.setBook(second);
            author3.setBook(third);
            session.persist(author1);
            session.persist(author2);
            session.persist(author3);
            session.remove(author2);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
