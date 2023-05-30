package com.zenika.gestion_salles.controller.imp;

import com.zenika.gestion_salles.controller.ReunionController;
import com.zenika.gestion_salles.dto.ReunionDto;
import com.zenika.gestion_salles.service.ReunionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReunionControllerImpl implements ReunionController {

    private  final ReunionService reunionService;
    @Override
    public ResponseEntity<ReunionDto> reserveSalle(ReunionDto reunionDto) {
        return new ResponseEntity<>(reunionService.reserveSalle(reunionDto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpStatus> cancelReunion(long reunionId) {

        reunionService.cancelReunion(reunionId);

        return  new ResponseEntity<>(HttpStatus.OK);

    }

    @Override
    public ResponseEntity<ReunionDto> updateReunion(long reunionId, ReunionDto dto) {

        return new ResponseEntity<>(reunionService.updateReunion(reunionId,dto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ReunionDto>> getReunionsBySalle(String nomSalle) {

        return new ResponseEntity<>(reunionService.getReunionsBySalle(nomSalle), HttpStatus.OK);
    }
}
