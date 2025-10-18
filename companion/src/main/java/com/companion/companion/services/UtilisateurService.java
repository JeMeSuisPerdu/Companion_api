package com.companion.companion.services;

import com.companion.companion.entities.*;
import com.companion.companion.enums.*;
import com.companion.companion.repositories.*;
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
    /**
     * Inscription d'un établissement scolaire
     */
    public Utilisateur inscrireEcole(String prenom, String nom, String email, String password,
                                     String nomEcole, TypeEtablissement typeEtablissement,
                                     String localisation, String siteWeb, String emailContact,
                                     String telephone, String description) {

        if (utilisateurRepository.existsByEmail(email)) {
            throw new RuntimeException("Cet email est déjà utilisé");
        }

        String passwordHash = passwordEncoder.encode(password);
        Utilisateur utilisateur = new Utilisateur(prenom, nom, email, passwordHash, Role.ECOLE);

        // Créer le profil école (non vérifié par défaut)
        ProfilEcole profil = new ProfilEcole(nomEcole, typeEtablissement, localisation, description, utilisateur);
        profil.setSiteWeb(siteWeb);
        profil.setEmailContact(emailContact);
        profil.setTelephone(telephone);

        utilisateur.setProfilEcole(profil); // À ajouter dans Utilisateur.java

        return utilisateurRepository.save(utilisateur);
    }
    /**
     * Inscription complète d'une entreprise partenaire
     * Crée l'utilisateur + profil entreprise avec système de vérification
     */
    public Utilisateur inscrireEntreprise(String prenom, String nom, String email, String password,
                                          String nomEntreprise, String secteurActivite, String localisation,
                                          String description, Integer taille) {

        if (utilisateurRepository.existsByEmail(email)) {
            throw new RuntimeException("Cet email est déjà utilisé");
        }

        String passwordHash = passwordEncoder.encode(password);
        Utilisateur utilisateur = new Utilisateur(prenom, nom, email, passwordHash, Role.ENTREPRISE);

        // Créer le profil entreprise (non vérifié par défaut)
        ProfilEntreprise profil = new ProfilEntreprise(nomEntreprise, secteurActivite, localisation,
                description, taille, utilisateur);

        utilisateur.setProfilEntreprise(profil); // À ajouter dans Utilisateur.java

        return utilisateurRepository.save(utilisateur);
    }
}