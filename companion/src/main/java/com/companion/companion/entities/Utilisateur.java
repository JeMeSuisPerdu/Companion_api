package com.companion.companion.entities;

import com.companion.companion.enums.Role;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="utilisateur")
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false,length=128)
    private String nom;
    @Column(nullable=false,length=128)
    private String prenom;
    @Column(nullable=false,unique = true)
    private String email;
    @Column(nullable=false)
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private Role role;
    @Column(nullable=false)
    private LocalDateTime dateCreation;
    private Boolean actif = true;

    @OneToOne(mappedBy = "utilisateur", cascade = CascadeType.ALL)
    private ProfilEtudiant profilEtudiant;
    @OneToOne(mappedBy = "utilisateur", cascade = CascadeType.ALL)
    private ProfilEcole profilEcole;
    @OneToOne(mappedBy = "utilisateur", cascade = CascadeType.ALL)
    private ProfilEntreprise profilEntreprise;
    public Utilisateur() {

    }
    // Constructeur avec paramètres
    public Utilisateur(String prenom, String nom, String email, String password, Role role) {
        this.prenom = prenom;
        this.nom = nom;
        this.email = email;
        this.password = password;
        this.dateCreation = LocalDateTime.now(); // ← Garantit la bonne date
        this.role = role;
    }

    //----------------------------  Getters/Setters  ----------------------------
    public Long getId() {
        return id;
    }

    public void setNom(String nom){
        this.nom = nom;
    }
    public String getNom()
    {
        return nom;
    }

    public void setPrenom(String prenom){
        this.prenom = prenom;
    }
    public String getPrenom()
    {
        return prenom;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }
    public String getEmail()
    {
        return email;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
    public String getPassword()
    {
        return password;
    }

    public void setRole(Role role)
    {
        this.role = role;
    }
    public Role getRole()
    {
        return role;
    }

    public void setDateCreation(LocalDateTime dateCreation)
    {
        this.dateCreation = dateCreation;
    }
    public LocalDateTime getDateCreation()
    {
        return dateCreation;
    }

    public void setActif(boolean actif)
    {
        this.actif =actif;
    }
    public boolean isActif()
    {
        return actif;
    }
    public void setProfilEtudiant(ProfilEtudiant profilEtudiant) {
        this.profilEtudiant = profilEtudiant;
    }

    public ProfilEtudiant getProfilEtudiant() {
        return profilEtudiant;
    }
    public void setProfilEcole(ProfilEcole profilEcole) {
        this.profilEcole = profilEcole;
    }
    public ProfilEcole getProfilEcole() {
        return profilEcole;
    }

    public ProfilEntreprise getProfilEntreprise() {
        return profilEntreprise;
    }
    public void setProfilEntreprise(ProfilEntreprise profilEntreprise) {
        this.profilEntreprise = profilEntreprise;
    }
}