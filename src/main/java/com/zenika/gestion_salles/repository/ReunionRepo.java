package com.zenika.gestion_salles.repository;

import com.zenika.gestion_salles.entity.Reunion;
import com.zenika.gestion_salles.entity.Salle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface ReunionRepo extends JpaRepository<Reunion,Long> {


    List<Reunion> getBySalleAndDateDebutGreaterThan(Salle salle, LocalDateTime date );

    Long countBySalleAndDateDebutGreaterThan(Salle salle, LocalDateTime dateDebut);


}
