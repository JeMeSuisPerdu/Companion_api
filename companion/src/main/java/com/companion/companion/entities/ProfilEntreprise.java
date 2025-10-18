package com.companion.companion.entities;

import jakarta.persistence.*;

/**
 * Profil des entreprises partenaires
 * Proposent des stages, alternances et emplois aux étudiants
 * Avec système de vérification pour garantir l'authenticité
 */
@Entity
@Table(name = "profil_entreprise")
public class ProfilEntreprise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* === INFORMATIONS ENTREPRISE === */
    @Column(nullable = false)
    private String nomEntreprise;

    @Column(nullable = false)
    private String secteurActivite;

    @Column(nullable = false)
    private String localisation;

    private String siteWeb;
    private String emailContact;
    private String telephone;

    /* === DESCRIPTION ET TAILLE === */
    @Column(columnDefinition = "TEXT")
    private String description;

    private Integer taille; // Nombre d'employés

    /* === SYSTÈME DE VÉRIFICATION === */
    private boolean verifie = false;           // ← Vérifiée par l'équipe Companion
    private String justificatifVerification;   // ← URL du document justificatif (KBIS, etc.)
    private String messageVerification;        // ← Message de l'équipe ("Vérifiée le 17/10/2025")

    /* === RELATION AVEC L'UTILISATEUR === */
    @OneToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;

    //----------------------------  CONSTRUCTEURS  ----------------------------
    public ProfilEntreprise() {}

    public ProfilEntreprise(String nomEntreprise, String secteurActivite,
                            String localisation, String description,
                            Integer taille, Utilisateur utilisateur) {
        this.nomEntreprise = nomEntreprise;
        this.secteurActivite = secteurActivite;
        this.localisation = localisation;
        this.description = description;
        this.taille = taille;
        this.utilisateur = utilisateur;
    }

    //----------------------------  GETTERS/SETTERS  ----------------------------
    public Long getId() { return id; }

    public String getNomEntreprise() { return nomEntreprise; }
    public void setNomEntreprise(String nomEntreprise) { this.nomEntreprise = nomEntreprise; }

    public String getSecteurActivite() { return secteurActivite; }
    public void setSecteurActivite(String secteurActivite) { this.secteurActivite = secteurActivite; }

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

    public Integer getTaille() { return taille; }
    public void setTaille(Integer taille) { this.taille = taille; }

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
     * Marque cette entreprise comme vérifiée par l'équipe Companion
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

    /**
     * Détermine si c'est une grande entreprise
     */
    public boolean estGrandeEntreprise() {
        return taille != null && taille > 500;
    }

    /**
     * Détermine si c'est une PME
     */
    public boolean estPME() {
        return taille != null && taille <= 500;
    }
}