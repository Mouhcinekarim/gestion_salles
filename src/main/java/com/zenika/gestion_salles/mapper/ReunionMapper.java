package com.zenika.gestion_salles.mapper;

import com.zenika.gestion_salles.dto.ReunionDto;
import com.zenika.gestion_salles.entity.Reunion;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


public interface ReunionMapper {

    Reunion dtoToReunion(ReunionDto dto);

    ReunionDto reunionToDto(Reunion reunion);
}
