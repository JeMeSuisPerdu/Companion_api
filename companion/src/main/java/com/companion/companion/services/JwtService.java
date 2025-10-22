package com.companion.companion.services;

import com.companion.companion.entities.*;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

/**
 * Service pour la gestion des tokens JWT
 * Génère, valide et extrait les informations des tokens
 */
@Service
public class JwtService {

    /* === CONFIGURATION JWT === */
    private final SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final long expiration = 86400000; // 24 heures en millisecondes

    /**
     * Génère un token JWT pour un utilisateur
     *
     * @param utilisateur L'utilisateur pour lequel générer le token
     * @return Token JWT signé
     */
    public String generateToken(Utilisateur utilisateur) {
        return Jwts.builder()
                .setSubject(utilisateur.getEmail())           // Email comme sujet
                .claim("id", utilisateur.getId())             // ID utilisateur
                .claim("role", utilisateur.getRole())         // Rôle utilisateur
                .setIssuedAt(new Date())                      // Date de création
                .setExpiration(new Date(System.currentTimeMillis() + expiration)) // Expiration
                .signWith(secretKey)                          // Signature
                .compact();
    }

    /**
     * Valide un token JWT
     *
     * @param token Le token à valider
     * @return true si le token est valide, false sinon
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Extrait l'email du token JWT
     *
     * @param token Le token JWT
     * @return L'email de l'utilisateur
     */
    public String extractEmail(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    /**
     * Extrait le rôle de l'utilisateur du token
     *
     * @param token Le token JWT
     * @return Le rôle de l'utilisateur
     */
    public String extractRole(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.get("role", String.class);
    }

    /**
     * Extrait l'ID de l'utilisateur du token
     *
     * @param token Le token JWT
     * @return L'ID de l'utilisateur
     */
    public Long extractUserId(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.get("id", Long.class);
    }
}