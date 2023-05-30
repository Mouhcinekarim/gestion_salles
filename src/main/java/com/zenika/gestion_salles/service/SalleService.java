package com.zenika.gestion_salles.service;

import com.zenika.gestion_salles.dto.SalleDto;

import java.util.List;

public interface SalleService {

    /**
     * Ajoute une nouvelle salle.
     *
     * @param dto les informations de la salle à ajouter
     * @return le DTO de la salle ajoutée
     */
    SalleDto addSalle(SalleDto dto);

    /**
     * Met à jour les informations d'une salle existante.
     *
     * @param id  l'ID de la salle à mettre à jour
     * @param dto les nouvelles informations de la salle
     * @return le DTO de la salle mise à jour
     */
    SalleDto updateSalle(long id, SalleDto dto);

    /**
     * Supprime une salle.
     *
     * @param id l'ID de la salle à supprimer
     */
    void deleteSalle(long id);

    /**
     * Récupère toutes les salles.
     *
     * @return la liste des DTO des salles disponibles
     */
    List<SalleDto> getAllSalle();
}
