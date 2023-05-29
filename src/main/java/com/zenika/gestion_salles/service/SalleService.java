package com.zenika.gestion_salles.service;

import com.zenika.gestion_salles.dto.SalleDto;
import com.zenika.gestion_salles.enums.Equipement;

import java.util.List;

public interface SalleService {
    SalleDto addSalle(SalleDto dto);
    SalleDto updateSalle(long id,SalleDto dto);
    void deleteSalle(long  id);
    List<SalleDto> getAllSalle();


}
