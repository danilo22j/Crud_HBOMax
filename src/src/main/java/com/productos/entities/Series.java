
package com.productos.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

@Entity
@Table(name="tb_series")
public class Series {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_serie", nullable=false)
    private int id;
    
    @Column(name="nombre_ser", nullable=false)
    private String nombre;
    
    // RELACIÓN: Una serie tiene muchas temporadas
    @OneToMany(mappedBy = "serie", cascade = CascadeType.ALL)
    private List<Temporadas> temporadas;

    public Series() {
    }

    public Series(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public List<Temporadas> getTemporadas() {
        return temporadas;
    }

    public void setTemporadas(List<Temporadas> temporadas) {
        this.temporadas = temporadas;
    }
}
