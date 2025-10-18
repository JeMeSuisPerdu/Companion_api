package com.companion.companion.dto;

import com.companion.companion.enums.*;

/**
 * DTO pour l'inscription des établissements d'enseignement
 */
public class InscriptionEcoleRequest extends InscriptionRequest {
    private String nomEcole;
    private TypeEtablissement typeEtablissement;
    private String localisation;
    private String siteWeb;
    private String emailContact;
    private String telephone;
    private String description;

    //----------------------------  Constructeurs  ----------------------------
    public InscriptionEcoleRequest() {}

    public InscriptionEcoleRequest(String prenom, String nom, String email, String password,
                                   String nomEcole, TypeEtablissement typeEtablissement,
                                   String localisation, String siteWeb, String emailContact,
                                   String telephone, String description) {
        super(prenom, nom, email, password, Role.ECOLE); // Toujours ECOLE
        this.nomEcole = nomEcole;
        this.typeEtablissement = typeEtablissement;
        this.localisation = localisation;
        this.siteWeb = siteWeb;
        this.emailContact = emailContact;
        this.telephone = telephone;
        this.description = description;
    }

    //----------------------------  Getters/Setters  ----------------------------
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
}