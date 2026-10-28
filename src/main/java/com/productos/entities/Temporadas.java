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
@Table(name = "tb_temporadas")
public class Temporadas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tempo")
    private int id;
    
    @Column(name = "num_tempo", nullable = false)
    private int numeroTemporada;
    
    // Relacion: Muchas temporadas pertenecen a una Serie
    @ManyToOne
    @JoinColumn(name = "serie_id", nullable = false) // Nombre de la columna FK en la base de datos
    private Series serie; 
    
    @jakarta.persistence.OneToMany(mappedBy = "temporada", cascade = jakarta.persistence.CascadeType.ALL)
    private java.util.List<Episodios> episodios;

    public Temporadas() {
    }

    public Temporadas(int numeroTemporada, Series serie) {
        this.numeroTemporada = numeroTemporada;
        this.serie = serie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumeroTemporada() {
        return numeroTemporada;
    }

    public void setNumeroTemporada(int numeroTemporada) {
        this.numeroTemporada = numeroTemporada;
    }

    public Series getSerie() {
        return serie;
    }

    public void setSerie(Series serie) {
        this.serie = serie;
    }
    
    public java.util.List<Episodios> getEpisodios() {
        return episodios;
    }

    public void setEpisodios(java.util.List<Episodios> episodios) {
        this.episodios = episodios;
    }
}
