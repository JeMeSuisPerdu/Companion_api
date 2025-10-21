package com.companion.companion.dto;

import com.companion.companion.entities.Utilisateur;

public class LoginResponse {
    private String token;
    private Utilisateur utilisateur;

    LoginResponse() {}
    LoginResponse(String token, Utilisateur utilisateur) {
        this.token = token;
        this.utilisateur = utilisateur;
    }

    public String getToken() {
        return token;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }
}