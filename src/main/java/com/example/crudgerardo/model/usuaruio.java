package com.example.crudgerardo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "usuario")

public class usuaruio {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Column(name = "nombre")
    String nombre;

    @Column(name = "a_paterno")
    String a_paterno;

    @Column(name = "a_materno")
    String a_materno;

    @Column(name = "id_role")
    int id_role;

    public usuaruio() {
    }
    public usuaruio(String nombre, String a_paterno, String a_materno, int id_role) {
        this.nombre = nombre;
        this.a_paterno = a_paterno;
        this.a_materno = a_materno;
        this.id_role = id_role;
    }

    
    public usuaruio(int id, String nombre, String a_paterno, String a_materno, int id_role) {
        this.id = id;
        this.nombre = nombre;
        this.a_paterno = a_paterno;
        this.a_materno = a_materno;
        this.id_role = id_role;
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
    public String getA_paterno() {
        return a_paterno;
    }
    public void setA_paterno(String a_paterno) {
        this.a_paterno = a_paterno;
    }
    public String getA_materno() {
        return a_materno;
    }
    public void setA_materno(String a_materno) {
        this.a_materno = a_materno;
    }
    public int getId_role() {
        return id_role;
    }
    public void setId_role(int id_role) {
        this.id_role = id_role;
    }



}
