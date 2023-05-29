package com.zenika.gestion_salles.controller;

import com.zenika.gestion_salles.dto.ReunionDto;
import com.zenika.gestion_salles.dto.SalleDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/salle")
public interface SalleController {
    @PostMapping("add")
    ResponseEntity<SalleDto> addSalle(@RequestBody SalleDto dto);

    @PutMapping("update/{id}")
    ResponseEntity<SalleDto> updateSalle(@PathVariable long salleId, @RequestBody SalleDto dto);

    @DeleteMapping("{id}")
    ResponseEntity<HttpStatus> deleteSalle(long id);

    @GetMapping("all")
    ResponseEntity<List<SalleDto>> getAllSalles();


}
