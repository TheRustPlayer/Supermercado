package com.supermercado.dao;

import com.supermercado.modelos.Empleado;
import com.supermercado.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class EmpleadoDAO {

    public Empleado validarLogin(String usuario, String password) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Empleado WHERE usuario = :user AND password = :pass AND activo = true";
            Query<Empleado> query = session.createQuery(hql, Empleado.class);
            query.setParameter("user", usuario);
            query.setParameter("pass", password);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * REGISTRO: Guarda un nuevo empleado en la base de datos. ESTE ES EL MÉTODO
     * QUE TE FALTABA
     */
    public void registrar(Empleado empleado) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // 1. Iniciamos la transacción (modo seguro)
            transaction = session.beginTransaction();

            // 2. Guardamos el objeto
            session.persist(empleado);

            // 3. Confirmamos los cambios (Guardar definitivo)
            transaction.commit();
        } catch (Exception e) {
            // Si algo falla, deshacemos todo para no romper la DB
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            // Lanzamos el error para que el botón sepa que falló y muestre el mensaje
            throw new RuntimeException("Error al registrar: " + e.getMessage());
        }
    }
    // En EmpleadoDAO.java

    /**
     * MODIFICAR: Busca por DNI y actualiza los datos que no estén vacíos.
     */
    public boolean modificar(String dniBuscar, String nuevoNombre, String nuevoApellido, String nuevaPass) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            // 1. Buscar el empleado por DNI
            String hql = "FROM Empleado WHERE dni = :dni";
            Query<Empleado> query = session.createQuery(hql, Empleado.class);
            query.setParameter("dni", dniBuscar);
            Empleado emp = query.uniqueResult();

            // Si no existe, devolvemos false para avisar al usuario
            if (emp == null) {
                return false;
            }

            // 2. Actualizar datos (solo si el usuario escribió algo)
            if (nuevoNombre != null && !nuevoNombre.trim().isEmpty()) {
                emp.setNombre(nuevoNombre);
            }
            if (nuevoApellido != null && !nuevoApellido.trim().isEmpty()) {
                emp.setApellido(nuevoApellido);
            }
            if (nuevaPass != null && !nuevaPass.trim().isEmpty()) {
                emp.setPassword(nuevaPass);
            }

            // 3. Guardar cambios
            session.merge(emp); // merge actualiza el registro existente
            transaction.commit();
            return true;

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }
    // En EmpleadoDAO.java

    /**
     * LISTAR: Devuelve todos los empleados para llenar el desplegable.
     */
    public java.util.List<Empleado> listarTodos() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // HQL simple para traer todo
            return session.createQuery("FROM Empleado", Empleado.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return new java.util.ArrayList<>(); // Devuelve lista vacía si falla
        }
    }

    /**
     * MODIFICAR AVANZADO: Permite cambiar también el DNI.
     */
    public boolean modificar(String dniOriginal, String nuevoDni, String nuevoNombre, String nuevoApellido, String nuevaPass) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            // 1. Buscamos por el DNI ORIGINAL (el que tenía antes de tocar nada)
            String hql = "FROM Empleado WHERE dni = :dni";
            Query<Empleado> query = session.createQuery(hql, Empleado.class);
            query.setParameter("dni", dniOriginal);
            Empleado emp = query.uniqueResult();

            if (emp == null) {
                return false;
            }

            // 2. Actualizamos datos
            // Importante: Si cambiamos el DNI, también cambiamos el USUARIO automáticamente
            if (nuevoDni != null && !nuevoDni.trim().isEmpty()) {
                emp.setDni(nuevoDni);
                emp.setUsuario(nuevoDni); // <--- REQUISITO CUMPLIDO: Usuario igual a DNI
            }

            if (nuevoNombre != null && !nuevoNombre.trim().isEmpty()) {
                emp.setNombre(nuevoNombre);
            }
            if (nuevoApellido != null && !nuevoApellido.trim().isEmpty()) {
                emp.setApellido(nuevoApellido);
            }
            if (nuevaPass != null && !nuevaPass.trim().isEmpty()) {
                emp.setPassword(nuevaPass);
            }

            session.merge(emp);
            transaction.commit();
            return true;

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }
// En EmpleadoDAO.java

    /**
     * ELIMINAR: Borra un empleado de la base de datos. Nota: Si el empleado
     * tiene ventas asignadas, esto podría fallar por seguridad (Foreign Keys).
     */
    public boolean eliminar(String dni) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            // 1. Buscar al empleado
            String hql = "FROM Empleado WHERE dni = :dni";
            Query<Empleado> query = session.createQuery(hql, Empleado.class);
            query.setParameter("dni", dni);
            Empleado emp = query.uniqueResult();

            // Si no existe, no podemos borrarlo
            if (emp == null) {
                return false;
            }

            // 2. Eliminar
            session.remove(emp);

            transaction.commit();
            return true;

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            // Si hay error (ej: tiene ventas asociadas), imprimimos el error
            e.printStackTrace();
            return false;
        }
    }
}
