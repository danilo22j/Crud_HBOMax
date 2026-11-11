package com.productos.crud;

import com.productos.entities.Usuarios;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class crud_Usuarios {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.productos_CRUD_HBOMax_jar_1.0-SNAPSHOTPU");

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void crear(Usuarios nuevoUsuario) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(nuevoUsuario);
            em.getTransaction().commit();
            System.out.println("Usuario registrado con exito!");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public List<Usuarios> leerTodos() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT u FROM Usuarios u", Usuarios.class).getResultList();
        } finally {
            em.close();
        }
    }

    public Usuarios buscarPorId(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Usuarios.class, id);
        } finally {
            em.close();
        }
    }

    public void actualizar(Usuarios usuarioModificado) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(usuarioModificado);
            em.getTransaction().commit();
            System.out.println("Cuenta de usuario actualizada con exito!");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void eliminar(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Usuarios userAEliminar = em.find(Usuarios.class, id);
            if (userAEliminar != null) {
                em.remove(userAEliminar);
                em.getTransaction().commit();
                System.out.println("Cuenta de usuario eliminada con exito!");
            } else {
                System.out.println("No se encontro ningun usuario con el ID proporcionado.");
            }
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
