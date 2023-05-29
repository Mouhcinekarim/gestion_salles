package com.zenika.gestion_salles.service.imp;

import com.zenika.gestion_salles.dto.SalleDto;
import com.zenika.gestion_salles.entity.Salle;
import com.zenika.gestion_salles.mapper.SalleMapper;
import com.zenika.gestion_salles.repository.SalleRepo;
import com.zenika.gestion_salles.service.SalleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SalleServiceImpl implements SalleService {

    private final SalleRepo salleRepo;
    private final SalleMapper salleMapper;


    @Override
    public SalleDto addSalle(SalleDto dto) {

        Salle salle=salleMapper.dtoToSalle(dto);
        salle=Optional.of(salleRepo.save(salle)).
                orElseThrow(()->new RuntimeException("Salle Alyread exits"));

        return salleMapper.salleToDto(salle);
    }

    @Override
    public SalleDto updateSalle(long id,SalleDto dto) {
        Salle salleUpdate=salleRepo.findById(id).orElseThrow(RuntimeException::new);

        Salle salle=salleMapper.dtoToSalle(dto);

        Optional.of(salle.getCapacite()).ifPresent(salleUpdate::setCapacite);
        Optional.of(salle.getEquipements()).ifPresent(salleUpdate::setEquipements);


        return salleMapper.salleToDto(salleRepo.save(salleUpdate));
    }

    @Override
    public void deleteSalle(long id) {
        salleRepo.deleteById(id);
    }

    @Override
    public List<SalleDto> getAllSalle() {
        return salleRepo.findAll().stream().map(salleMapper::salleToDto).toList();
    }

}
