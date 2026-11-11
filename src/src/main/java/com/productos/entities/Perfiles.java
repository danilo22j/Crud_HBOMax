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
@Table(name = "tb_perfiles")
public class Perfiles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_perfil")
    private int id;

    @Column(name = "nombre_perfil", nullable = false)
    private String nombre;

    @Column(name = "es_kids", nullable = false)
    private boolean esKids; // Control parental (true/false)

    // Relacion de muchos perfiles pertenecen a una sola cuenta de Usuario principal
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false) // Columna de la llave foránea en la BD
    private Usuarios usuario;

    public Perfiles() {
    }
    
    public Perfiles(String nombre, boolean esKids, Usuarios usuario) {
        this.nombre = nombre;
        this.esKids = esKids;
        this.usuario = usuario;
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

    public boolean isEsKids() {
        return esKids;
    }

    public void setEsKids(boolean esKids) {
        this.esKids = esKids;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }
    
}
