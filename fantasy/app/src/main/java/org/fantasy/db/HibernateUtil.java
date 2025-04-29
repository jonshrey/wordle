package org.fantasy.db;

import org.fantasy.model.Player;
import org.fantasy.model.Team;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
    private static SessionFactory sessionFactory;
    static {
        final StandardServiceRegistry registry =
            new StandardServiceRegistryBuilder()
                    .build();
        try {
            sessionFactory = new MetadataSources(registry)             
                        .addAnnotatedClasses(Team.class, Player.class)   
                        .buildMetadata()                  
                        .buildSessionFactory(); 
        } catch(Exception e) {
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
