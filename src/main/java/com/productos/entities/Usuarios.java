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
@Table(name = "tb_usuarios")
public class Usuarios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private int id;

    @Column(name = "nombres_user", nullable = false)
    private String nombres;
    
    @Column(name = "email_user", nullable = false, unique = true)
    private String email;

    @Column(name = "password_user", nullable = false)
    private String password;

    @Column(name = "estado_user")
    private String estado; // Ej: "Activo", "Suspendido", "Cancelado"

    // Relación: Un usuario principal puede tener muchos perfiles (Max permite hasta 5)
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Perfiles> perfiles;

    public Usuarios() {
    }

    public Usuarios(String nombres, String email, String password, String estado) {
    this.nombres = nombres;
    this.email = email;
    this.password = password;
    this.estado = estado;
}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<Perfiles> getPerfiles() {
        return perfiles;
    }

    public void setPerfiles(List<Perfiles> perfiles) {
        this.perfiles = perfiles;
    }
}
