package com.zenika.gestion_salles.enums;

import lombok.Getter;

import java.util.List;
@Getter
public enum TypeReunion {
    VC(List.of(Equipement.PIEUVRE,Equipement.ECRAN,Equipement.WEBCAM)),
    SPEC(List.of(Equipement.TABLEAU)),
    RS(List.of()),
    RC(List.of(Equipement.TABLEAU,Equipement.ECRAN,Equipement.PIEUVRE));

    private TypeReunion(List<Equipement> equipement){
        this.equipement=equipement;
    }

    List<Equipement> equipement;
}
