package com.productos.crud;

import com.productos.entities.Series;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class crud_Series {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.productos_CRUD_HBOMax_jar_1.0-SNAPSHOTPU");

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void crear(Series nuevaSerie) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(nuevaSerie);
            em.getTransaction().commit();
            System.out.println("Serie guardada con exito!");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    // 2. READ ALL (Listar todas las series registradas)
    public List<Series> leerTodas() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT s FROM Series s", Series.class).getResultList();
        } finally {
            em.close();
        }
    }

    // 3. READ BY ID (Buscar una serie específica)
    public Series buscarPorId(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Series.class, id);
        } finally {
            em.close();
        }
    }

    // 4. UPDATE (Modificar el nombre de la serie)
    public void modificar(int id, String nuevoNombre) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Series serieAModificar = em.find(Series.class, id);
            if (serieAModificar != null) {
                serieAModificar.setNombre(nuevoNombre);
                em.getTransaction().commit();
                System.out.println("Serie actualizada con exito!");
            } else {
                System.out.println("No se encontro ninguna serie con el ID proporcionado.");
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

    // 5. DELETE (Eliminar una serie)
    public void eliminar(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Series serieAEliminar = em.find(Series.class, id);
            if (serieAEliminar != null) {
                em.remove(serieAEliminar);
                em.getTransaction().commit();
                System.out.println("Serie eliminada con exito!");
            } else {
                System.out.println("No se encontro ninguna serie con el ID proporcionado.");
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
