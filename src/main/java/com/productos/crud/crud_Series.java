package com.productos.crud;

import com.productos.entities.Series;
import com.productos.entities.Temporadas;
import com.productos.entities.Episodios;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class crud_Series {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.productos_CRUD_HBOMax_jar_1.0-SNAPSHOTPU");

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    //Ingresar o Crear
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

    //Leer o Mostrar
    public List<Series> leerTodas() {
        EntityManager em = emf.createEntityManager();
        try {
            //Trae las series con sus temporadas
            List<Series> series = em.createQuery(
                    "SELECT DISTINCT s FROM Series s LEFT JOIN FETCH s.temporadas", Series.class)
                    .getResultList();

            //Carga los episodios de esas temporadas en la misma sesion
            if (!series.isEmpty()) {
                em.createQuery(
                        "SELECT DISTINCT t FROM Temporadas t LEFT JOIN FETCH t.episodios WHERE t.serie IN :series", Temporadas.class)
                        .setParameter("series", series)
                        .getResultList();
            }
            return series;
        } finally {
            em.close();
        }
    }

    //Buscar 
    public Series buscarPorId(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Series.class, id);
        } finally {
            em.close();
        }
    }

    //Actualizar o Modificar
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

    //Eliminar
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
    
    //Lista de catalogo por Busqueda y Ordenada 
    public List<Series> listarSeriesFiltradas(String filtroNombre, int pagina, int tamanoPagina) {
        EntityManager em = emf.createEntityManager();
        try {
            //Consulta JPQL con filtro, ordenamiento y paginación
            String jpql = "SELECT s FROM Series s "
                    + "WHERE LOWER(s.nombre) LIKE LOWER(:filtro) "
                    + "ORDER BY s.nombre ASC";

            int primerRegistro = (pagina - 1) * tamanoPagina;

            List<Series> listaSeries = em.createQuery(jpql, Series.class)
                    .setParameter("filtro", "%" + filtroNombre + "%")
                    .setFirstResult(primerRegistro)
                    .setMaxResults(tamanoPagina)
                    .getResultList();

            //Cargar temporadas y episodios con JPQL para esas series
            if (!listaSeries.isEmpty()) {
                em.createQuery("SELECT DISTINCT s FROM Series s LEFT JOIN FETCH s.temporadas WHERE s IN :series", Series.class)
                        .setParameter("series", listaSeries)
                        .getResultList();

                em.createQuery("SELECT DISTINCT t FROM Temporadas t LEFT JOIN FETCH t.episodios WHERE t.serie IN :series", Temporadas.class)
                        .setParameter("series", listaSeries)
                        .getResultList();
            }

            return listaSeries;
        } finally {
            em.close();
        }
    }
}
