package com.zenika.gestion_salles.dto;

import com.zenika.gestion_salles.enums.TypeReunion;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
public class ReunionDto {
    private long id;
    private TypeReunion type;
    private Integer nbrPersonEnSite;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private String salleName;
}
