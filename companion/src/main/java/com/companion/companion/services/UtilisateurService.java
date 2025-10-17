package com.companion.companion.services;

import com.companion.companion.entities.CentreInteret;
import com.companion.companion.entities.Competence;
import com.companion.companion.entities.ProfilEtudiant;
import com.companion.companion.entities.Utilisateur;
import com.companion.companion.enums.Role;
import com.companion.companion.repositories.CentreInteretRepository;
import com.companion.companion.repositories.CompetenceRepository;
import com.companion.companion.repositories.UtilisateurRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service de gestion des utilisateurs
 * Contient la logique métier pour l'inscription et la gestion des comptes
 */
@Service
public class UtilisateurService {

    /* === DEPENDANCES INJECTEES PAR SPRING === */
    private final UtilisateurRepository utilisateurRepository;    // Accès à la table utilisateur
    private final PasswordEncoder passwordEncoder;                // Hashage des mots de passe
    private final CompetenceRepository competenceRepository;      // Accès à la table competence
    private final CentreInteretRepository centreInteretRepository; // Accès à la table centre_interet

    /**
     * Constructeur avec injection des dépendances
     * Spring fournit automatiquement les instances
     */
    public UtilisateurService(UtilisateurRepository utilisateurRepository,
                              PasswordEncoder passwordEncoder,
                              CompetenceRepository competenceRepository,
                              CentreInteretRepository centreInteretRepository) {
        this.utilisateurRepository = utilisateurRepository;
        this.passwordEncoder = passwordEncoder;
        this.competenceRepository = competenceRepository;
        this.centreInteretRepository = centreInteretRepository;
    }

    /**
     * Inscription basique d'un utilisateur (tous rôles confondus)
     * Utilisé pour les écoles, entreprises et administrateurs
     *
     * @param prenom   Prénom de l'utilisateur
     * @param nom      Nom de l'utilisateur
     * @param email    Email unique
     * @param password Mot de passe en clair (sera hashé)
     * @param role     Rôle de l'utilisateur (ETUDIANT, ECOLE, ENTREPRISE, ADMIN)
     * @return Utilisateur créé et sauvegardé en base
     * @throws RuntimeException si l'email est déjà utilisé
     */
    public Utilisateur inscrireUtilisateur(String prenom, String nom, String email, String password, Role role) {
        /* Vérification de l'unicité de l'email */
        if (utilisateurRepository.existsByEmail(email)) {
            throw new RuntimeException("Cet email est déjà utilisé");
        }

        /* Hashage sécurisé du mot de passe */
        String passwordHash = passwordEncoder.encode(password);

        /* Création de l'utilisateur */
        Utilisateur utilisateur = new Utilisateur(prenom, nom, email, passwordHash, role);

        /* Sauvegarde en base de données */
        return utilisateurRepository.save(utilisateur);
    }

    /**
     * Inscription complète d'un étudiant avec son profil détaillé
     * Crée à la fois l'utilisateur ET son profil étudiant avec compétences/centres d'intérêt
     *
     * @param prenom          Prénom de l'étudiant
     * @param nom             Nom de l'étudiant
     * @param email           Email unique
     * @param password        Mot de passe en clair
     * @param niveauEtude     Niveau d'étude (ex: "Licence", "Master")
     * @param domaineEtude    Domaine d'étude (ex: "Informatique", "Commerce")
     * @param localisation    Ville ou région
     * @param competenceIds   Liste des IDs des compétences sélectionnées
     * @param centreInteretIds Liste des IDs des centres d'intérêt sélectionnés
     * @return Utilisateur créé avec son profil étudiant complet
     * @throws RuntimeException si l'email est déjà utilisé
     */
    public Utilisateur inscrireEtudiant(String prenom, String nom, String email, String password,
                                        String niveauEtude, String domaineEtude, String localisation,
                                        List<Long> competenceIds, List<Long> centreInteretIds) {

        /* Vérification de l'unicité de l'email */
        if (utilisateurRepository.existsByEmail(email)) {
            throw new RuntimeException("Cet email est déjà utilisé");
        }

        /* Hashage du mot de passe */
        String passwordHash = passwordEncoder.encode(password);

        /* Création de l'utilisateur étudiant */
        Utilisateur utilisateur = new Utilisateur(prenom, nom, email, passwordHash, Role.ETUDIANT);

        /* Récupération des compétences et centres d'intérêt depuis la base */
        List<Competence> competences = competenceRepository.findAllById(competenceIds);
        List<CentreInteret> centresInteret = centreInteretRepository.findAllById(centreInteretIds);

        /* Création du profil étudiant complet */
        ProfilEtudiant profil = new ProfilEtudiant(niveauEtude, domaineEtude, localisation,
                competences, centresInteret, utilisateur);
        utilisateur.setProfilEtudiant(profil);

        /* Sauvegarde en cascade : utilisateur + profil étudiant */
        return utilisateurRepository.save(utilisateur);
    }
}