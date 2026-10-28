package com.productos.crud;

import com.productos.entities.Episodios;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class crud_Episodios {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.productos_CRUD_HBOMax_jar_1.0-SNAPSHOTPU");

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public void crear(Episodios nuevoEpisodio) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(nuevoEpisodio);
            em.getTransaction().commit();
            System.out.println("Episodio guardado con exito!");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
    
    public List<Episodios> leerTodos() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT e FROM Episodios e", Episodios.class).getResultList();
        } finally {
            em.close();
        }
    }
    
    public Episodios buscarPorId(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Episodios.class, id);
        } finally {
            em.close();
        }
    }
    
    public void actualizar(Episodios episodioModificado) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(episodioModificado);
            em.getTransaction().commit();
            System.out.println("Episodio actualizado con exito!");
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
            Episodios epiAEliminar = em.find(Episodios.class, id);
            if (epiAEliminar != null) {
                em.remove(epiAEliminar);
                em.getTransaction().commit();
                System.out.println("¡Episodio eliminado con exito!");
            } else {
                System.out.println("No se encontro ningun episodio con el ID proporcionado.");
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
