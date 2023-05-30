package com.zenika.gestion_salles.controller;

import com.zenika.gestion_salles.dto.SalleDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("/salle")
public interface SalleController {

    @PostMapping("/add")
    @Operation(summary = "Add a salle", description = "This method allows you to add a salle.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The salle was added",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseEntity.class)))
    })
    ResponseEntity<SalleDto> addSalle(@RequestBody SalleDto dto);

    @PutMapping("/update/{id}")
    @Operation(summary = "Update a salle", description = "This method allows you to update a salle.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The salle was updated",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseEntity.class)))
    })
    ResponseEntity<SalleDto> updateSalle(@PathVariable("id") long salleId, @RequestBody SalleDto dto);

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a salle", description = "This method allows you to delete a salle.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The salle was deleted",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseEntity.class)))
    })
    ResponseEntity<HttpStatus> deleteSalle(@PathVariable("id") long id);

    @GetMapping("/all")
    @Operation(summary = "Get all salles", description = "This method allows you to get all salles.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The salles were found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseEntity.class)))
    })
    ResponseEntity<List<SalleDto>> getAllSalles();
}