package com.companion.companion.services;

import com.companion.companion.entities.Utilisateur;
import com.companion.companion.enums.Role;
import com.companion.companion.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Utilisateur inscrireUtilisateur(String prenom, String nom, String email, String password, Role role) {

        // vérifie si l'email existe déjà
        if (utilisateurRepository.existsByEmail(email)) {
            throw new RuntimeException("Cet email est déjà utilisé");
        }

        // hasher le mot de passe
        String passwordHash = passwordEncoder.encode(password);

        // créer l'utilisateur
        Utilisateur utilisateur = new Utilisateur(prenom, nom, email, passwordHash, role);

        // sauvegarder
        return utilisateurRepository.save(utilisateur);
    }
}