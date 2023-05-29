package com.zenika.gestion_salles.entity;

import com.zenika.gestion_salles.enums.Equipement;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Salle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nomSalle;
    private Integer capacite;
    private List<Equipement> equipements;
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "salle")
    private List<Reunion> reunions;
}
