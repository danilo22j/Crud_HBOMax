/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
@Table(name = "tb_historial_reproduccion")
public class Historial_Reproduccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_historial")
    private int id;

    @Column(name = "minuto_pausa", nullable = false)
    private int minutoPausa;

    @Column(name = "fecha_ultima_vista")
    private String fechaUltimaVista;

    @Column(name = "visto_completo")
    private boolean vistoCompleto;

    // Relacion de muchos registros de historial pertenecen a un solo Perfil
    @ManyToOne
    @JoinColumn(name = "perfil_id", nullable = false)
    private Perfiles perfil;

    // Realcion Opcional. Muchos registros de historial pueden apuntar a una Película
    @ManyToOne
    @JoinColumn(name = "pelicula_id", nullable = true)
    private Peliculas pelicula;

    // Realcion  Opcional. Muchos registros de historial pueden apuntar a un Episodio
    @ManyToOne
    @JoinColumn(name = "episodio_id", nullable = true)
    private Episodios episodio;

    public Historial_Reproduccion() {
    }

    public Historial_Reproduccion(int minutoPausa, String fechaUltimaVista, boolean vistoCompleto, Perfiles perfil) {
        this.minutoPausa = minutoPausa;
        this.fechaUltimaVista = fechaUltimaVista;
        this.vistoCompleto = vistoCompleto;
        this.perfil = perfil;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMinutoPausa() {
        return minutoPausa;
    }

    public void setMinutoPausa(int minutoPausa) {
        this.minutoPausa = minutoPausa;
    }

    public String getFechaUltimaVista() {
        return fechaUltimaVista;
    }

    public void setFechaUltimaVista(String fechaUltimaVista) {
        this.fechaUltimaVista = fechaUltimaVista;
    }

    public boolean isVistoCompleto() {
        return vistoCompleto;
    }

    public void setVistoCompleto(boolean vistoCompleto) {
        this.vistoCompleto = vistoCompleto;
    }

    public Perfiles getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfiles perfil) {
        this.perfil = perfil;
    }

    public Peliculas getPelicula() {
        return pelicula;
    }

    public void setPelicula(Peliculas pelicula) {
        this.pelicula = pelicula;
    }

    public Episodios getEpisodio() {
        return episodio;
    }

    public void setEpisodio(Episodios episodio) {
        this.episodio = episodio;
    }
}
