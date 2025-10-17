package com.companion.companion.dto;
import com.companion.companion.enums.Role;

import java.util.List;

public class InscriptionEtudiantRequest extends InscriptionRequest {
    private String niveauEtude;
    private String domaineEtude;
    private String localisation;
    private List<Long> competenceIds;
    private List<Long> centreInteretIds;

    //----------------------------  Constructeurs  ----------------------------
    public InscriptionEtudiantRequest() {}

    public InscriptionEtudiantRequest(String prenom, String nom, String email, String password,
                                      String niveauEtude, String domaineEtude, String localisation,
                                      List<Long> competenceIds, List<Long> centreInteretIds) {
        super(prenom, nom, email, password, Role.ETUDIANT); // Toujours ETUDIANT
        this.niveauEtude = niveauEtude;
        this.domaineEtude = domaineEtude;
        this.localisation = localisation;
        this.competenceIds = competenceIds;
        this.centreInteretIds = centreInteretIds;
    }

    //----------------------------  Getters/Setters  ----------------------------
    public String getNiveauEtude() { return niveauEtude; }
    public void setNiveauEtude(String niveauEtude) { this.niveauEtude = niveauEtude; }

    public String getDomaineEtude() { return domaineEtude; }
    public void setDomaineEtude(String domaineEtude) { this.domaineEtude = domaineEtude; }

    public String getLocalisation() { return localisation; }
    public void setLocalisation(String localisation) { this.localisation = localisation; }

    public List<Long> getCompetenceIds() { return competenceIds; }
    public void setCompetenceIds(List<Long> competenceIds) { this.competenceIds = competenceIds; }

    public List<Long> getCentreInteretIds() { return centreInteretIds; }
    public void setCentreInteretIds(List<Long> centreInteretIds) { this.centreInteretIds = centreInteretIds; }
}