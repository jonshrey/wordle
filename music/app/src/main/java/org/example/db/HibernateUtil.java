package org.example.db;

import org.example.model.Album;
import org.example.model.Artist;
import org.example.model.Song;
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
                        .addAnnotatedClasses(Artist.class, Album.class, Song.class)   
                        .buildMetadata()                  
                        .buildSessionFactory(); 
        } catch(Exception e) {
            System.out.println("If my data source is broken, this is where I'll get caught!");
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
