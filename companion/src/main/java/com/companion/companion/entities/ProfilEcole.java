package com.companion.companion.entities;

import com.companion.companion.enums.TypeEtablissement;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Profil des établissements d'enseignement (écoles, universités, etc.)
 * Avec système de vérification pour éviter les faux comptes
 */
@Entity
@Table(name = "profil_ecole")
public class ProfilEcole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* === INFORMATIONS OFFICIELLES === */
    @Column(nullable = false)
    private String nomEcole;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeEtablissement typeEtablissement;

    @Column(nullable = false)
    private String localisation;

    private String siteWeb;
    private String emailContact;
    private String telephone;

    @Column(columnDefinition = "TEXT")
    private String description;

    /* === SYSTÈME DE VÉRIFICATION === */
    private boolean verifie = false;           // ← Vérifié par l'équipe Companion
    private String justificatifVerification;   // ← URL du document justificatif
    private String messageVerification;        // ← Message de l'équipe ("Vérifié le 17/10/2025")

    /* === RELATION AVEC L'UTILISATEUR === */
    @OneToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;

    //----------------------------  CONSTRUCTEURS  ----------------------------

    public ProfilEcole() {}

    public ProfilEcole(String nomEcole, TypeEtablissement typeEtablissement,
                       String localisation, String description, Utilisateur utilisateur) {
        this.nomEcole = nomEcole;
        this.typeEtablissement = typeEtablissement;
        this.localisation = localisation;
        this.description = description;
        this.utilisateur = utilisateur;
    }

    //----------------------------  GETTERS/SETTERS  ----------------------------

    public Long getId() { return id; }

    public String getNomEcole() { return nomEcole; }
    public void setNomEcole(String nomEcole) { this.nomEcole = nomEcole; }

    public TypeEtablissement getTypeEtablissement() { return typeEtablissement; }
    public void setTypeEtablissement(TypeEtablissement typeEtablissement) { this.typeEtablissement = typeEtablissement; }

    public String getLocalisation() { return localisation; }
    public void setLocalisation(String localisation) { this.localisation = localisation; }

    public String getSiteWeb() { return siteWeb; }
    public void setSiteWeb(String siteWeb) { this.siteWeb = siteWeb; }

    public String getEmailContact() { return emailContact; }
    public void setEmailContact(String emailContact) { this.emailContact = emailContact; }

    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public boolean isVerifie() { return verifie; }
    public void setVerifie(boolean verifie) { this.verifie = verifie; }

    public String getJustificatifVerification() { return justificatifVerification; }
    public void setJustificatifVerification(String justificatifVerification) { this.justificatifVerification = justificatifVerification; }

    public String getMessageVerification() { return messageVerification; }
    public void setMessageVerification(String messageVerification) { this.messageVerification = messageVerification; }

    public Utilisateur getUtilisateur() { return utilisateur; }
    public void setUtilisateur(Utilisateur utilisateur) { this.utilisateur = utilisateur; }

    /* === MÉTHODES MÉTIER === */

    /**
     * Marque cette école comme vérifiée par l'équipe Companion
     */
    public void verifier(String justificatif, String message) {
        this.verifie = true;
        this.justificatifVerification = justificatif;
        this.messageVerification = message;
    }

    /**
     * Retire la vérification (en cas de problème)
     */
    public void annulerVerification() {
        this.verifie = false;
        this.justificatifVerification = null;
        this.messageVerification = null;
    }
}