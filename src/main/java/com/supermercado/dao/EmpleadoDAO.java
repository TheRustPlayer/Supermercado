package com.supermercado.dao;

import com.supermercado.modelos.Empleado;
import com.supermercado.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class EmpleadoDAO {

    /**
     * Busca un empleado por su usuario (DNI) y contraseña.
     * @return El objeto Empleado si es válido, o null si no existe.
     */
    public Empleado validarLogin(String usuario, String password) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // HQL: "from Empleado" se refiere a la clase Java, no a la tabla SQL
            String hql = "FROM Empleado WHERE usuario = :user AND password = :pass AND activo = true";
            Query<Empleado> query = session.createQuery(hql, Empleado.class);
            query.setParameter("user", usuario);
            query.setParameter("pass", password);

            // Retorna el resultado único o null si no encuentra nada
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}