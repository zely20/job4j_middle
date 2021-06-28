package ru.job4j.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HubRunModel {

    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            Model superb = Model.of("superb");
            Model rapid = Model.of("rapid");
            Model kodiaq = Model.of("kodiaq");
            Model octavia = Model.of("octavia");
            Model karoq = Model.of("karoq");
            MarkCar skoda = MarkCar.of("Skoda");
            skoda.setModels(superb);
            skoda.setModels(rapid);
            skoda.setModels(kodiaq);
            skoda.setModels(octavia);
            skoda.setModels(karoq);
            session.save(skoda);
            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
