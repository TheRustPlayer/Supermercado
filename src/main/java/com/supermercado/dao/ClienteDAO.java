package com.supermercado.dao;

import com.supermercado.modelos.Cliente;
import com.supermercado.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ClienteDAO {

    // Método para guardar nuevos clientes
    public void registrar(Cliente cliente) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(cliente);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw new RuntimeException("Error en ClienteDAO (registrar): " + e.getMessage());
        }
    }

    // Método para el login de clientes
    public Cliente validarLogin(String user, String pass) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Cliente WHERE usuario = :u AND password = :p AND activo = true", Cliente.class)
                    .setParameter("u", user)
                    .setParameter("p", pass)
                    .uniqueResult();
        } catch (Exception e) {
            return null;
        }
    }
}