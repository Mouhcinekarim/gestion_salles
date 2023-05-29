package com.zenika.gestion_salles.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SalleDto {

    private long id;
    private String nomSalle;
    private Integer capacite;
    private List<String> equipements;
}
