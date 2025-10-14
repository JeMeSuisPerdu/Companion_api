package com.companion.companion.dto;

import com.companion.companion.enums.Role;

public class InscriptionRequest {
    private String prenom;
    private String nom;
    private String email;
    private String password;
    private Role role;

    public InscriptionRequest() {}

    public InscriptionRequest(String prenom, String nom, String email, String password, Role role) {
        this.prenom = prenom;
        this.nom = nom;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // Getters et Setters
    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
}