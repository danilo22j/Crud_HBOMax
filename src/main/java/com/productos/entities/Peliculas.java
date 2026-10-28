
package com.productos.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tb_peliculas")
public class Peliculas {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_pelicula", nullable=false)
    private int id;
    
    @Column(name="nombre_peli", nullable=false)
    private String titulo;
    
    @Column(name="genero_peli")
    private String genero;
    
    @Column(name="fecha_estreno_peli")
    private String fecha_Estreno;
    
    @Column(name="duraccion_peli")
    private int tiempo_Min;

    public Peliculas(String titulo, String genero, String fecha_Estreno, int tiempo_Min) {
        this.titulo = titulo;
        this.genero = genero;
        this.fecha_Estreno = fecha_Estreno;
        this.tiempo_Min = tiempo_Min;
    }

    public Peliculas() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getFecha_Estreno() {
        return fecha_Estreno;
    }

    public void setFecha_Estreno(String fecha_Estreno) {
        this.fecha_Estreno = fecha_Estreno;
    }

    public int getTiempo_Min() {
        return tiempo_Min;
    }

    public void setTiempo_Min(int tiempo_Min) {
        this.tiempo_Min = tiempo_Min;
    }
}
