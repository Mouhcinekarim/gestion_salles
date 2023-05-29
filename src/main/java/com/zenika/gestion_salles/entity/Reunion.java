package com.zenika.gestion_salles.entity;

import com.zenika.gestion_salles.enums.TypeReunion;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Reunion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private TypeReunion type;
    private Integer nbrPersonEnSite;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    @ManyToOne()
    private Salle salle;
}
