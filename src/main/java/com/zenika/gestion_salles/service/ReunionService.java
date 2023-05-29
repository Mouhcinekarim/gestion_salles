package com.zenika.gestion_salles.service;

import com.zenika.gestion_salles.dto.ReunionDto;
import com.zenika.gestion_salles.entity.Reunion;

import java.util.List;


public interface ReunionService {

     ReunionDto reserveSalle(ReunionDto reunionDto);

     void cancelReunion(long reunionId);

     ReunionDto updateReunion(long reunionId,ReunionDto dto);

     Reunion searchPerfectReunion(ReunionDto reunionDto);

     List<ReunionDto> getReunionsBySalle(String nomSalle);

}
