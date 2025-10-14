package com.companion.companion;

import com.companion.companion.entities.Utilisateur;
import com.companion.companion.enums.Role;
import com.companion.companion.repositories.UtilisateurRepository;
import com.companion.companion.services.UtilisateurService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CompanionApplication {

    public static void main(String[] args) {
        SpringApplication.run(CompanionApplication.class, args);
    }

    @Bean
    public CommandLineRunner testUtilisateur(UtilisateurRepository utilisateurRepository, UtilisateurService utilisateurService) {
        return args -> {
//            System.out.println("🎯 Test du Service d'Inscription...");
//            // Utiliser un email DIFFÉRENT
//            Utilisateur usersaved = utilisateurService.inscrireUtilisateur("Marie", "Curie", "marie@test.com", "password123", Role.ETUDIANT);
//            System.out.println("✅ Utilisateur créé avec ID: " + usersaved.getId());
//            System.out.println("📧 Email: " + usersaved.getEmail());
//            System.out.println("🔐 Password hashé: " + usersaved.getPassword()); // Voir le hash
        };
    }
}