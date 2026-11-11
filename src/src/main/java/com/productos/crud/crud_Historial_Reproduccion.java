
package com.productos.crud;

import com.productos.entities.Historial_Reproduccion;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;


public class crud_Historial_Reproduccion {
    
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.productos_CRUD_HBOMax_jar_1.0-SNAPSHOTPU");
    
    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public void crear(Historial_Reproduccion nuevoHistorial) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(nuevoHistorial);
            em.getTransaction().commit();
            System.out.println("Progreso de reproduccion guardado con exito!");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
    
    public List<Historial_Reproduccion> leerTodos() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT h FROM Historial_Reproduccion h", Historial_Reproduccion.class).getResultList();
        } finally {
            em.close();
        }
    }
    
    public Historial_Reproduccion buscarPorId(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Historial_Reproduccion.class, id);
        } finally {
            em.close();
        }
    }
    
    public void actualizar(Historial_Reproduccion historialModificado) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(historialModificado);
            em.getTransaction().commit();
            System.out.println("Minuto de pausa y progreso actualizados con exito!");
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
            Historial_Reproduccion histAEliminar = em.find(Historial_Reproduccion.class, id);
            if (histAEliminar != null) {
                em.remove(histAEliminar);
                em.getTransaction().commit();
                System.out.println("Registro de historial eliminado con exito!");
            } else {
                System.out.println("No se encontro ningun registro de historial con el ID proporcionado.");
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
