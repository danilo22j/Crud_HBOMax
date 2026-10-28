package com.productos.crud;

import com.productos.entities.Perfiles;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;


public class crud_Perfiles {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.productos_CRUD_HBOMax_jar_1.0-SNAPSHOTPU");

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public void crear(Perfiles nuevoPerfil) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(nuevoPerfil);
            em.getTransaction().commit();
            System.out.println("Perfil guardado con exito!");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
    
    public List<Perfiles> leerTodos() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT p FROM Perfiles p", Perfiles.class).getResultList();
        } finally {
            em.close();
        }
    }
    
    public Perfiles buscarPorId(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Perfiles.class, id);
        } finally {
            em.close();
        }
    }
    
    public void actualizar(Perfiles perfilModificado) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(perfilModificado);
            em.getTransaction().commit();
            System.out.println("Perfil actualizado con exito!");
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
            Perfiles perfilAEliminar = em.find(Perfiles.class, id);
            if (perfilAEliminar != null) {
                em.remove(perfilAEliminar);
                em.getTransaction().commit();
                System.out.println("Perfil eliminado con exito!");
            } else {
                System.out.println("No se encontro ningun perfil con el ID proporcionado.");
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
