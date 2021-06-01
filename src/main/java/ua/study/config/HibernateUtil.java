package ua.study.config;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
    private static ServiceRegistry serviceRegistry;
    private static SessionFactory sessionFactory;

    static{
        try {
            Configuration configuration = new Configuration();
            serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory();
        } catch (Exception e) {
            System.out.println("Problem creating session factory");
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
