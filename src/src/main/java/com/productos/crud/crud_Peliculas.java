
package com.productos.crud;

import com.productos.entities.Peliculas;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class crud_Peliculas {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.productos_CRUD_HBOMax_jar_1.0-SNAPSHOTPU");
    
    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    //Ingresar
    public void crear(Peliculas p) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            System.out.println("Pelicula guardada con exito!");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    //Leer o Mostrar
    public List<Peliculas> leerTodas() {
        EntityManager em = getEntityManager();
        try {
            // Usamos JPQL para consultar la tabla
            return em.createQuery("SELECT p FROM Peliculas p", Peliculas.class).getResultList();
        } finally {
            em.close();
        }
    }

    //Buscar
    public Peliculas buscarPorId(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Peliculas.class, id);
        } finally {
            em.close();
        }
    }

    //Actualizar o Modificar
    public void actualizar(Peliculas p) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(p); // merge actualiza el registro existente basándose en su ID
            em.getTransaction().commit();
            System.out.println("Pelicula actualizada con exito!");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    //Eliminar
    public void eliminar(int id) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Peliculas p = em.find(Peliculas.class, id);
            if (p != null) {
                em.remove(em.merge(p));
                em.getTransaction().commit();
                System.out.println("Pelicula eliminada con exito!");
            } else {
                System.out.println("No se encontro ninguna pelicula con el ID: " + id);
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

    public void cerrar() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
