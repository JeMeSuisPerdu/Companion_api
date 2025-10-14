package com.companion.companion.services;

import com.companion.companion.entities.Utilisateur;
import com.companion.companion.enums.Role;
import com.companion.companion.repositories.UtilisateurRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;

    public UtilisateurService(UtilisateurRepository utilisateurRepository, PasswordEncoder passwordEncoder) {
        this.utilisateurRepository = utilisateurRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Utilisateur inscrireUtilisateur(String prenom, String nom, String email, String password, Role role) {
        // Vérifier si l'email existe déjà
        if (utilisateurRepository.existsByEmail(email)) {
            throw new RuntimeException("Cet email est déjà utilisé");
        }

        // Hasher le mot de passe
        String passwordHash = passwordEncoder.encode(password);

        // Créer l'utilisateur
        Utilisateur utilisateur = new Utilisateur(prenom, nom, email, passwordHash, role);

        // Sauvegarder
        return utilisateurRepository.save(utilisateur);
    }
}