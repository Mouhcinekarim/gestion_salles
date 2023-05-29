package com.zenika.gestion_salles.controller.imp;

import com.zenika.gestion_salles.controller.SalleController;
import com.zenika.gestion_salles.dto.ReunionDto;
import com.zenika.gestion_salles.dto.SalleDto;
import com.zenika.gestion_salles.service.SalleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SalleControllerImpl implements SalleController {

    private final SalleService salleService;

    @Override
    public ResponseEntity<SalleDto> addSalle(SalleDto dto) {

        return new ResponseEntity<>(salleService.addSalle(dto), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<SalleDto> updateSalle(long salleId, SalleDto dto) {

        return new ResponseEntity<>(salleService.updateSalle(salleId,dto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpStatus> deleteSalle(long id) {

        salleService.deleteSalle(id);

        return  new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<SalleDto>> getAllSalles() {

        return new ResponseEntity<>(salleService.getAllSalle(), HttpStatus.OK);
    }
}
