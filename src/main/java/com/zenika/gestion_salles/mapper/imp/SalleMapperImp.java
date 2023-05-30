package com.zenika.gestion_salles.mapper.imp;

import com.zenika.gestion_salles.dto.SalleDto;
import com.zenika.gestion_salles.entity.Salle;
import com.zenika.gestion_salles.enums.Equipement;
import com.zenika.gestion_salles.mapper.SalleMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SalleMapperImp implements SalleMapper {
    @Override
    public SalleDto salleToDto(Salle salle) {

        return SalleDto.builder()
                .capacite(salle.getCapacite())
                .equipements(
                        Optional.of(salle.getEquipements()).orElse(null)
                                .stream().map(Enum::name).toList()
                        )
                .nomSalle(salle.getNomSalle())
                .build();
    }

    @Override
    public Salle dtoToSalle(SalleDto dto) {

        return Salle.builder()
                .capacite(dto.getCapacite())
                .nomSalle(dto.getNomSalle())
                .equipements(
                        Optional.of(dto.getEquipements()).orElse(null).stream()
                                .map(Equipement::valueOf).toList()
                )
                .build();
    }
}
