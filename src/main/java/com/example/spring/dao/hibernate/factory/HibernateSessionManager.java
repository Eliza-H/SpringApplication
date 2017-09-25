package com.example.spring.dao.hibernate.factory;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

/**
 * Created by elh on 19.09.17.
 */
@Component

public class HibernateSessionManager {
    private static StandardServiceRegistry registry;
    private static SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                registry = new StandardServiceRegistryBuilder().configure().build();

                final MetadataSources sources = new MetadataSources(registry);

                final Metadata metadata = sources.getMetadataBuilder().build();

                sessionFactory = metadata.getSessionFactoryBuilder().build();
            } catch (Exception e) {
                shutDown();
                throw e;
            }
        }
        return sessionFactory;
    }

    public static void shutDown() {
        if (sessionFactory != null && sessionFactory.isOpen()) {
            sessionFactory.close();
            sessionFactory = null;
        }
        if (registry != null) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

}