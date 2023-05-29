package com.zenika.gestion_salles.controller;

import com.zenika.gestion_salles.dto.ReunionDto;
import com.zenika.gestion_salles.entity.Reunion;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("reunion")
public interface ReunionController {
    @PostMapping("reserveSalle")
    ResponseEntity<ReunionDto> reserveSalle(@RequestBody ReunionDto reunionDto);
    @DeleteMapping("{reunionId}")
    ResponseEntity cancelReunion(@PathVariable long reunionId);
    @PutMapping("{reunionId}")
    ResponseEntity<ReunionDto>  updateReunion(@PathVariable long reunionId,@RequestBody ReunionDto dto);
    @GetMapping("sale/{nomSalle}")
    ResponseEntity<List<ReunionDto>> getReunionsBySalle(@PathVariable String nomSalle);

}
