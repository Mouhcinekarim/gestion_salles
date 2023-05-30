package com.zenika.gestion_salles.mapper;

import com.zenika.gestion_salles.dto.ReunionDto;
import com.zenika.gestion_salles.entity.Reunion;



public interface ReunionMapper {

    Reunion dtoToReunion(ReunionDto dto);

    ReunionDto reunionToDto(Reunion reunion);
}
