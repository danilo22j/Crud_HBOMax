package com.productos.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_episodios")
public class Episodios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_episodio")
    private int id;

    @Column(name = "numero_epi", nullable = false)
    private int numeroEpisodio;

    @Column(name = "nombre_epi", nullable = false)
    private String nombre;

    @Column(name = "duraccion_epi")
    private int duracion;

    // Relacion muchos episodios pertenecen a una Temporada
    @ManyToOne
    @JoinColumn(name = "tempo_id", nullable = false)
    private Temporadas temporada;

    public Episodios() {
    }

    public Episodios(int numeroEpisodio, String nombre, int duracion, Temporadas temporada) {
        this.numeroEpisodio = numeroEpisodio;
        this.nombre = nombre;
        this.duracion = duracion;
        this.temporada = temporada;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumeroEpisodio() {
        return numeroEpisodio;
    }

    public void setNumeroEpisodio(int numeroEpisodio) {
        this.numeroEpisodio = numeroEpisodio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public Temporadas getTemporada() {
        return temporada;
    }

    public void setTemporada(Temporadas temporada) {
        this.temporada = temporada;
    }
}
