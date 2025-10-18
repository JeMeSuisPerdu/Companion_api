package com.companion.companion.dto;

import com.companion.companion.enums.Role;

/**
 * DTO pour l'inscription des entreprises partenaires
 */
public class InscriptionEntrepriseRequest extends InscriptionRequest {
    private String nomEntreprise;
    private String secteurActivite;
    private String localisation;
    private String siteWeb;
    private String emailContact;
    private String telephone;
    private String description;
    private Integer taille;

    //----------------------------  CONSTRUCTEURS  ----------------------------

    public InscriptionEntrepriseRequest() {}

    public InscriptionEntrepriseRequest(String prenom, String nom, String email, String password,
                                        String nomEntreprise, String secteurActivite, String localisation,
                                        String siteWeb, String emailContact, String telephone,
                                        String description, Integer taille) {
        super(prenom, nom, email, password, Role.ENTREPRISE);
        this.nomEntreprise = nomEntreprise;
        this.secteurActivite = secteurActivite;
        this.localisation = localisation;
        this.siteWeb = siteWeb;
        this.emailContact = emailContact;
        this.telephone = telephone;
        this.description = description;
        this.taille = taille;
    }

    //----------------------------  GETTERS/SETTERS  ----------------------------

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
}