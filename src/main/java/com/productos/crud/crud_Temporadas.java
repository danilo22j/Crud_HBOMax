package com.productos.crud;

import com.productos.entities.Temporadas;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class crud_Temporadas {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.productos_CRUD_HBOMax_jar_1.0-SNAPSHOTPU");

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void crear(Temporadas nuevaTemporada) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(nuevaTemporada);
            em.getTransaction().commit();
            System.out.println("Temporada guardada con exito!");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public List<Temporadas> leerTodas() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT t FROM Temporadas t", Temporadas.class).getResultList();
        } finally {
            em.close();
        }
    }
    
    public Temporadas buscarPorId(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Temporadas.class, id);
        } finally {
            em.close();
        }
    }
    
    public void actualizar(Temporadas temporadaModificada) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(temporadaModificada);
            em.getTransaction().commit();
            System.out.println("Temporada actualizada con exito!");
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
            Temporadas tempoAEliminar = em.find(Temporadas.class, id);
            if (tempoAEliminar != null) {
                em.remove(tempoAEliminar);
                em.getTransaction().commit();
                System.out.println("Temporada eliminada con exito!");
            } else {
                System.out.println("No se encontro ninguna temporada con el ID proporcionado.");
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
