package com.supermercado.dao;

import com.supermercado.modelos.Log;
import com.supermercado.modelos.Empleado;
import com.supermercado.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class LogDAO {

    // 1. GUARDAR UN LOG (Lo usaremos desde Login y EmpleadoDAO)
    public void registrarLog(Empleado empleado, String accion) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Log nuevoLog = new Log(empleado, accion);
            session.persist(nuevoLog);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    // 2. LEER LOGS DE SESIONES (Para "REGISTRO DIARIO")
    public List<Log> obtenerLogsLogin() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Traemos los últimos 50 logs que sean de inicio de sesión
            return session.createQuery("FROM Log WHERE accion LIKE '%Inicio de sesión%' ORDER BY fecha DESC", Log.class)
                          .setMaxResults(50)
                          .list();
        }
    }

    // 3. LEER LOGS DE CAMBIOS (Para "MODIFICACIÓN")
    public List<Log> obtenerLogsModificaciones() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Traemos acciones que NO sean inicio de sesión (crear, borrar, modificar)
            return session.createQuery("FROM Log WHERE accion NOT LIKE '%Inicio de sesión%' ORDER BY fecha DESC", Log.class)
                          .setMaxResults(50)
                          .list();
        }
    }
}