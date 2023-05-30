package com.zenika.gestion_salles.dto;

import com.zenika.gestion_salles.enums.TypeReunion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReunionDto {
    private long id;
    private TypeReunion type;
    private Integer nbrPersonEnSite;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private String salleName;
}
