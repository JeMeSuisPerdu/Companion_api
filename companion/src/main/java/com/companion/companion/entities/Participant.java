package com.companion.companion.entities;
import com.companion.companion.enums.RoleParticipant;
import jakarta.persistence.*;

/**
 * Représente la participation d'un utilisateur à une conversation
 * Gère les rôles et permissions dans les conversations
 */
@Entity
@Table(name = "participant")
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* === RÔLE ET STATUT === */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleParticipant role = RoleParticipant.MEMBRE;

    /* === RELATIONS === */
    @ManyToOne
    @JoinColumn(name = "utilisateur_id", nullable = false)
    private Utilisateur utilisateur;

    @ManyToOne
    @JoinColumn(name = "conversation_id", nullable = false)
    private Conversation conversation;

    //----------------------------  CONSTRUCTEURS  ----------------------------

    public Participant() {}

    public Participant(Utilisateur utilisateur, Conversation conversation) {
        this.utilisateur = utilisateur;
        this.conversation = conversation;
    }

    public Participant(Utilisateur utilisateur, Conversation conversation, RoleParticipant role) {
        this.utilisateur = utilisateur;
        this.conversation = conversation;
        this.role = role;
    }

    //----------------------------  GETTERS/SETTERS  ----------------------------

    public Long getId() { return id; }

    public RoleParticipant getRole() { return role; }
    public void setRole(RoleParticipant role) { this.role = role; }

    public Utilisateur getUtilisateur() { return utilisateur; }
    public void setUtilisateur(Utilisateur utilisateur) { this.utilisateur = utilisateur; }

    public Conversation getConversation() { return conversation; }
    public void setConversation(Conversation conversation) { this.conversation = conversation; }

    /* === MÉTHODES MÉTIER === */

    /**
     * Vérifie si le participant est administrateur de la conversation
     */
    public boolean estAdministrateur() {
        return role == RoleParticipant.ADMINISTRATEUR;
    }

    /**
     * Vérifie si le participant est un membre simple
     */
    public boolean estMembre() {
        return role == RoleParticipant.MEMBRE;
    }

    /**
     * Donne les droits d'administration au participant
     */
    public void promouvoirAdministrateur() {
        this.role = RoleParticipant.ADMINISTRATEUR;
    }

    /**
     * Retire les droits d'administration
     */
    public void retirerAdministration() {
        this.role = RoleParticipant.MEMBRE;
    }
}