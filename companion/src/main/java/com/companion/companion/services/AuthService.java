package com.companion.companion.services;

import com.companion.companion.dto.LoginRequest;
import com.companion.companion.dto.LoginResponse;
import com.companion.companion.entities.Utilisateur;
import com.companion.companion.repositories.UtilisateurRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UtilisateurRepository utilisateurRepository,
                       PasswordEncoder passwordEncoder,
                       JwtService jwtService) {
        this.utilisateurRepository = utilisateurRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    /**
     * Authentifie un utilisateur et génère un token JWT
     */
    public LoginResponse authenticate(LoginRequest request) {
        // Trouver l'utilisateur par email
        Utilisateur utilisateur = utilisateurRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        // Vérifier le mot de passe
        if (!passwordEncoder.matches(request.getPassword(), utilisateur.getPassword())) {
            throw new RuntimeException("Mot de passe incorrect");
        }

        // Vérifier que le compte est actif
        if (!utilisateur.isActif()) {
            throw new RuntimeException("Compte désactivé");
        }

        // Générer le token JWT
        String token = jwtService.generateToken(utilisateur);

        return new LoginResponse(token,"Connexion Réussie",utilisateur);
    }
}