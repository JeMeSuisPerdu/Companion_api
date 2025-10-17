package com.companion.companion;

import com.companion.companion.entities.Competence;
import com.companion.companion.entities.CentreInteret;
import com.companion.companion.repositories.CompetenceRepository;
import com.companion.companion.repositories.CentreInteretRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final CompetenceRepository competenceRepository;
    private final CentreInteretRepository centreInteretRepository;

    public DataLoader(CompetenceRepository competenceRepository,
                      CentreInteretRepository centreInteretRepository) {
        this.competenceRepository = competenceRepository;
        this.centreInteretRepository = centreInteretRepository;
    }

    @Override
    public void run(String... args) {
        // Peupler les compétences
        if (competenceRepository.count() == 0) {
            competenceRepository.saveAll(List.of(
                    new Competence("Java"),
                    new Competence("Spring Boot"),
                    new Competence("Python"),
                    new Competence("JavaScript"),
                    new Competence("React"),
                    new Competence("Angular"),
                    new Competence("SQL"),
                    new Competence("Git")
            ));
        }

        // Peupler les centres d'intérêt
        if (centreInteretRepository.count() == 0) {
            centreInteretRepository.saveAll(List.of(
                    new CentreInteret("Développement Web"),
                    new CentreInteret("Intelligence Artificielle"),
                    new CentreInteret("Mobile"),
                    new CentreInteret("DevOps"),
                    new CentreInteret("UI/UX Design"),
                    new CentreInteret("Data Science"),
                    new CentreInteret("Cybersécurité"),
                    new CentreInteret("Open Source")
            ));
        }
    }
}