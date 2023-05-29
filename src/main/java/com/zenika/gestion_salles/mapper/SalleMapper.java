package com.zenika.gestion_salles.mapper;

import com.zenika.gestion_salles.dto.SalleDto;
import com.zenika.gestion_salles.entity.Salle;
public interface SalleMapper {

    SalleDto salleToDto(Salle sale);
    Salle dtoToSalle(SalleDto dto);

}
