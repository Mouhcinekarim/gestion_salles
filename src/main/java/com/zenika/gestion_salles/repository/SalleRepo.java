package com.zenika.gestion_salles.repository;

import com.zenika.gestion_salles.entity.Salle;
import com.zenika.gestion_salles.enums.Equipement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SalleRepo extends JpaRepository<Salle,Long> {
    Optional<Salle> findByNomSalle(String nom);

}
