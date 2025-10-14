package com.companion.companion.controllers;

import com.companion.companion.dto.InscriptionRequest;
import com.companion.companion.entities.Utilisateur;
import com.companion.companion.services.UtilisateurService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UtilisateurService utilisateurService;

    public AuthController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @PostMapping("/inscription")
    public ResponseEntity<?> inscrire(@RequestBody InscriptionRequest request) {
        try {
            Utilisateur utilisateur = utilisateurService.inscrireUtilisateur(
                    request.getPrenom(),
                    request.getNom(),
                    request.getEmail(),
                    request.getPassword(),
                    request.getRole()
            );
            return ResponseEntity.ok(utilisateur);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Erreur: " + e.getMessage());
        }
    }
}