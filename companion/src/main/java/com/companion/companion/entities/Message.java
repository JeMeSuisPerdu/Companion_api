package com.companion.companion.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Représente un message individuel dans une conversation
 */
@Entity
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* === CONTENU DU MESSAGE === */
    @Column(columnDefinition = "TEXT", nullable = false)
    private String contenu;

    private LocalDateTime dateEnvoi = LocalDateTime.now();

    /* === RELATIONS === */
    @ManyToOne
    @JoinColumn(name = "auteur_id", nullable = false)
    private Utilisateur auteur;

    @ManyToOne
    @JoinColumn(name = "conversation_id", nullable = false)
    private Conversation conversation;

    //----------------------------  CONSTRUCTEURS  ----------------------------

    public Message() {}

    public Message(String contenu, Utilisateur auteur, Conversation conversation) {
        this.contenu = contenu;
        this.auteur = auteur;
        this.conversation = conversation;
    }

    //----------------------------  GETTERS/SETTERS  ----------------------------

    public Long getId() { return id; }

    public String getContenu() { return contenu; }
    public void setContenu(String contenu) { this.contenu = contenu; }

    public LocalDateTime getDateEnvoi() { return dateEnvoi; }
    public void setDateEnvoi(LocalDateTime dateEnvoi) { this.dateEnvoi = dateEnvoi; }

    public Utilisateur getAuteur() { return auteur; }
    public void setAuteur(Utilisateur auteur) { this.auteur = auteur; }

    public Conversation getConversation() { return conversation; }
    public void setConversation(Conversation conversation) { this.conversation = conversation; }

    /* === MÉTHODES MÉTIER === */

    /**
     * Vérifie si le message a été envoyé récemment (moins de 5 minutes)
     */
    public boolean estRecent() {
        return dateEnvoi.isAfter(LocalDateTime.now().minusMinutes(5));
    }

    /**
     * Formate la date d'envoi pour l'affichage
     */
    public String getDateFormatee() {
        // Implémentation à faire selon le format souhaité
        return dateEnvoi.toString();
    }
}