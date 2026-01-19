package com.supermercado.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // Intenta leer el archivo hibernate.cfg.xml
            // y crea la conexión con la base de datos
            return new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            // Si falla (ej: MySQL está apagado o la contraseña está mal),
            // nos muestra el error en la consola
            System.err.println("Error crítico al crear SessionFactory: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    public static void shutdown() {
        // Cierra las cachés y las conexiones
        getSessionFactory().close();
    }
}