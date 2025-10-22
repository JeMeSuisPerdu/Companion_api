package com.companion.companion.dto;

import com.companion.companion.entities.Utilisateur;

public class LoginResponse {
    private String token;
    private String message;
    private Utilisateur utilisateur;

    // Constructeurs
    public LoginResponse() {}

    public LoginResponse(String token, String message, Utilisateur utilisateur) {
        this.token = token;
        this.message = message;
        this.utilisateur = utilisateur;
    }

    // Getters/Setters
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public Utilisateur getUtilisateur() { return utilisateur; }
    public void setUtilisateur(Utilisateur utilisateur) { this.utilisateur = utilisateur; }
}