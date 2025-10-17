package com.companion.companion.entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="profil_etudiant")
public class ProfilEtudiant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String niveauEtude;
    private String domaineEtude;
    private String localisation;

    @ManyToMany
    @JoinTable(name = "etudiant_competences",
            joinColumns = @JoinColumn(name = "profil_etudiant_id"),
            inverseJoinColumns = @JoinColumn(name = "competence_id"))
    private List<Competence> competences = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "etudiant_centres_interet",
            joinColumns = @JoinColumn(name = "profil_etudiant_id"),
            inverseJoinColumns = @JoinColumn(name = "centre_interet_id"))
    private List<CentreInteret> centresInteret = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "utilisateur_id")
    Utilisateur utilisateur;
    //----------------------------  Constructeur /s  ----------------------------

    public ProfilEtudiant() {}

    public ProfilEtudiant(String niveauEtude, String domaineEtude, String localisation, Utilisateur utilisateur) {
        this.niveauEtude = niveauEtude;
        this.domaineEtude = domaineEtude;
        this.localisation = localisation;
        this.utilisateur = utilisateur;
    }

    public ProfilEtudiant(String niveauEtude, String domaineEtude, String localisation,
                          List<Competence> competences, List<CentreInteret> centresInteret, Utilisateur utilisateur) {
        this(niveauEtude, domaineEtude, localisation, utilisateur);
        this.competences = competences;
        this.centresInteret = centresInteret;
    }
    //----------------------------  Getters/Setters  ----------------------------

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Long getId() {
        return id;
    }

    public String getNiveauEtude() {
        return niveauEtude;
    }

    public void setNiveauEtude(String niveauEtude) {
        this.niveauEtude = niveauEtude;
    }

    public String getDomaineEtude() {
        return domaineEtude;
    }

    public void setDomaineEtude(String domaineEtude) {
        this.domaineEtude = domaineEtude;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public List<Competence> getCompetences() {
        return competences;
    }

    public void setCompetences(List<Competence> competences) {
        this.competences = competences;
    }

    public List<CentreInteret> getCentresInteret() {
        return centresInteret;
    }

    public void setCentresInteret(List<CentreInteret> centresInteret) {
        this.centresInteret = centresInteret;
    }
}
