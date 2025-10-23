package com.companion.companion.entities;
import com.companion.companion.enums.TypeConversation;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "conversation")
public class Conversation {
    @Id
    private Long id;

    /* --------------- INFORMATIONS DE LA CONVERSATION --------------- */
    @Column(nullable=false)
    private String titre;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeConversation type;

    @Column(nullable=false)
    private LocalDateTime dateCreation;
    /* -------------------------- RELATIONS -------------------------- */
    @OneToMany(mappedBy = "conversation", cascade = CascadeType.ALL)
    private List<Message> messages = new ArrayList<>();

    @OneToMany(mappedBy = "conversation", cascade = CascadeType.ALL)
    private List<Participant> participants = new ArrayList<>();
    /* ------------------------ CONSTRUCTEUR -------------------------- */

    public  Conversation() {}
    public Conversation(String titre, TypeConversation type, LocalDateTime dateCreation) {
        this.titre = titre;
        this.type=type;
        this.dateCreation= LocalDateTime.now();
    }
    /* ----------------------------  GETTERS/SETTERS  ---------------------------- */
    public Long getId() { return id; }

    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }

    public TypeConversation getType() { return type; }
    public void setType(TypeConversation type) { this.type = type; }

    public LocalDateTime getDateCreation() { return dateCreation; }
    public void setDateCreation(LocalDateTime dateCreation) { this.dateCreation = dateCreation; }

    public List<Message> getMessages() { return messages; }
    public void setMessages(List<Message> messages) { this.messages = messages; }

    public List<Participant> getParticipants() { return participants; }
    public void setParticipants(List<Participant> participants) { this.participants = participants; }
    /* ----------------------------  METHODES METIER A AJOUTER  ---------------------------- */

}
