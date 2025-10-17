package com.companion.companion.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "centre_interet")
public class CentreInteret {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String nom;

    //----------------------------  Constructeur /s  ----------------------------
    public CentreInteret() {}

    public CentreInteret(String nom) {
        this.nom = nom;
    }

    //----------------------------  Getters/Setters  ----------------------------
    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}