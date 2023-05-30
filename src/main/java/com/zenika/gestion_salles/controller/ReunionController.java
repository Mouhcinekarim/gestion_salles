package com.zenika.gestion_salles.controller;

import com.zenika.gestion_salles.dto.ReunionDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("/reunion")
public interface ReunionController {

    @PostMapping("/reserveSalle")
    @Operation(summary = "Reserve a salle for a reunion", description = "This method allows you to reserve a salle for a reunion.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The salle was reserved",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseEntity.class)))
    })
    ResponseEntity<ReunionDto> reserveSalle(@RequestBody ReunionDto reunionDto);

    @DeleteMapping("/{reunionId}")
    @Operation(summary = "Cancel a reunion", description = "This method allows you to cancel a reunion.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The reunion was canceled",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseEntity.class)))
    })
    ResponseEntity<HttpStatus> cancelReunion(@PathVariable long reunionId);

    @PutMapping("/{reunionId}")
    @Operation(summary = "Update a reunion", description = "This method allows you to update a reunion.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The reunion was updated",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseEntity.class)))
    })
    ResponseEntity<ReunionDto> updateReunion(@PathVariable long reunionId, @RequestBody ReunionDto dto);

    @GetMapping("/sale/{nomSalle}")
    @Operation(summary = "Get reunions by salle", description = "This method allows you to get reunions by salle.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The reunions were found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseEntity.class)))
    })
    ResponseEntity<List<ReunionDto>> getReunionsBySalle(@PathVariable String nomSalle);
}
