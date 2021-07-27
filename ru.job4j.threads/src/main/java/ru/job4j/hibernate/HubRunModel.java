package ru.job4j.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.ArrayList;
import java.util.List;

public class HubRunModel {

    public static void main(String[] args) {

        List<MarkCar> markCars = new ArrayList<>();
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

            session.save(skoda);
            session.save(new Model("superb", session.get(MarkCar.class, 1)));
            session.save(new Model("rapid", session.get(MarkCar.class, 1)));

            markCars = session.createQuery(
                    "select distinct mc from MarkCar mc join fetch mc.models"
            ).list();
            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
        for (Model model : markCars.get(0).getModels()) {
            System.out.println(model);
        }
    }
}
