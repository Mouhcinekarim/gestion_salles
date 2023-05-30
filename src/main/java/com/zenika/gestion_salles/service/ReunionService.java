package com.zenika.gestion_salles.service;

import com.zenika.gestion_salles.dto.ReunionDto;
import com.zenika.gestion_salles.entity.Reunion;

import java.util.List;


public interface ReunionService {
     /**
      * Réserve une salle pour une réunion.
      *
      * @param reunionDto les détails de la réunion à réserver
      * @return le DTO de la réunion réservée
      */
     ReunionDto reserveSalle(ReunionDto reunionDto);
     /**
      * Annule une réunion.
      *
      * @param reunionId l'ID de la réunion à annuler
      */
     void cancelReunion(long reunionId);
     /**
      * Met à jour les informations d'une réunion.
      *
      * @param reunionId l'ID de la réunion à mettre à jour
      * @param dto les nouvelles informations de la réunion
      * @return le DTO de la réunion mise à jour
      */
     ReunionDto updateReunion(long reunionId,ReunionDto dto);
     /**
      * Recherche une réunion parfaite en fonction des critères spécifiés.
      *
      * @param reunionDto les critères de recherche de la réunion parfaite
      * @return la réunion parfaite trouvée
      */
     Reunion searchPerfectReunion(ReunionDto reunionDto);
     /**
      * Récupère la liste des réunions pour une salle donnée.
      *
      * @param nomSalle le nom de la salle pour laquelle récupérer les réunions
      * @return la liste des DTO des réunions pour la salle spécifiée
      */

     List<ReunionDto> getReunionsBySalle(String nomSalle);

}
