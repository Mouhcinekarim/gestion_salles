package com.zenika.gestion_salles.mapper.imp;

import com.zenika.gestion_salles.dto.ReunionDto;
import com.zenika.gestion_salles.entity.Reunion;
import com.zenika.gestion_salles.mapper.ReunionMapper;
import org.springframework.stereotype.Component;

@Component
public class ReunionMapperImpl implements ReunionMapper {
    @Override
    public Reunion dtoToReunion(ReunionDto dto) {
        Reunion reunion= new Reunion();
        reunion.setType(dto.getType());
        reunion.setNbrPersonEnSite(dto.getNbrPersonEnSite());
        return reunion;
    }

    @Override
    public ReunionDto reunionToDto(Reunion reunion) {


        return ReunionDto.builder()
                .type(reunion.getType())
                .dateDebut(reunion.getDateDebut())
                .dateFin(reunion.getDateFin())
                .nbrPersonEnSite(reunion.getNbrPersonEnSite())
                .salleName(reunion.getSalle().getNomSalle())
                .build();
    }
}
