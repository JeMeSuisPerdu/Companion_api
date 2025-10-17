package com.companion.companion.repositories;
import com.companion.companion.entities.CentreInteret;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CentreInteretRepository extends JpaRepository<CentreInteret, Long> {


}
