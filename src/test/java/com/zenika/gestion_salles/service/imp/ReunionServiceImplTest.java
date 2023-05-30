package com.zenika.gestion_salles.service.imp;

import com.zenika.gestion_salles.dto.ReunionDto;
import com.zenika.gestion_salles.entity.Reunion;
import com.zenika.gestion_salles.entity.Salle;
import com.zenika.gestion_salles.enums.Equipement;
import com.zenika.gestion_salles.enums.TypeReunion;
import com.zenika.gestion_salles.exception.ReunionNotFoundAnySalle;
import com.zenika.gestion_salles.mapper.ReunionMapper;
import com.zenika.gestion_salles.repository.ReunionRepo;
import com.zenika.gestion_salles.repository.SalleRepo;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@ExtendWith(MockitoExtension.class)
class ReunionServiceImplTest {


    @Mock
    private ReunionRepo reunionRepo;

    @Mock
    private SalleRepo salleRepo;

    @Mock
    private ReunionMapper reunionMapper;

    @InjectMocks
    private ReunionServiceImpl reunionService;


    //Scénario de réservation réussie d'une réunions simples
    @Test
    void reserveReunionRS(){
        ReunionDto reunionDto = new ReunionDto();
        reunionDto.setType(TypeReunion.RS);
        reunionDto.setNbrPersonEnSite(5);

        Reunion reunion = new Reunion();
        reunion.setId(1L);
        reunion.setType(TypeReunion.RS);
        reunion.setNbrPersonEnSite(5);

        Mockito.when(reunionMapper.dtoToReunion(reunionDto)).thenReturn(reunion);
        Mockito.when(reunionRepo.save(Mockito.any(Reunion.class))).thenReturn(reunion);
        Mockito.when(reunionRepo.countBySalleAndDateDebutGreaterThan(Mockito.any(Salle.class), Mockito.any (LocalDateTime.class))).thenReturn(0L);
        Mockito.when(reunionRepo.getBySalleAndDateDebutGreaterThan(Mockito.any(Salle.class),Mockito.any (LocalDateTime.class))).thenReturn(new ArrayList<>());

        Mockito.when(salleRepo.findAll()).thenReturn(getListeSalles());

        // Appel de la méthode à tester
        ReunionDto result = reunionService.reserveSalle(reunionDto);

        // Vérification des résultats
        Assertions.assertThat(reunion.getSalle().getNomSalle()).isEqualTo("E1001");

    }


    //Scénario de réservation réussie d'une d'une réunions  visioconférences
    @Test
    void reserveReunionVS(){
        ReunionDto reunionDto = new ReunionDto();
        reunionDto.setType(TypeReunion.VC);
        reunionDto.setNbrPersonEnSite(5);

        Reunion reunion = new Reunion();
        reunion.setId(1L);
        reunion.setType(TypeReunion.VC);
        reunion.setNbrPersonEnSite(5);

        Mockito.when(reunionMapper.dtoToReunion(reunionDto)).thenReturn(reunion);
        Mockito.when(reunionRepo.save(Mockito.any(Reunion.class))).thenReturn(reunion);
        Mockito.when(reunionRepo.getBySalleAndDateDebutGreaterThan(Mockito.any(Salle.class),Mockito.any (LocalDateTime.class))).thenReturn(new ArrayList<>());

        Mockito.when(salleRepo.findAll()).thenReturn(getListeSalles());

        // Appel de la méthode à tester
        ReunionDto result = reunionService.reserveSalle(reunionDto);

        // Vérification des résultats
        Assertions.assertThat(reunion.getSalle().getNomSalle()).isEqualTo("E3001");

    }
    //Scénario de réservation echoué d'une réunion  couplées
    @Test
    void reserveReunionRC(){
        ReunionDto reunionDto = new ReunionDto();
        reunionDto.setType(TypeReunion.RC);
        reunionDto.setNbrPersonEnSite(5);

        Reunion reunion = new Reunion();
        reunion.setId(1L);
        reunion.setType(TypeReunion.RC);
        reunion.setNbrPersonEnSite(5);

        Mockito.when(reunionMapper.dtoToReunion(reunionDto)).thenReturn(reunion);




        Mockito.when(salleRepo.findAll()).thenReturn(getListeSalles());

        // Appel de la méthode à tester
        org.junit.jupiter.api.Assertions.assertThrows(ReunionNotFoundAnySalle.class,()->reunionService.reserveSalle(reunionDto));
        // Vérification des résultats




    }
    //Scénario de réservation réussie d'une réunion de la séance de partage et d'études de cas
    @Test
    void reserveReunionSPEC(){
        ReunionDto reunionDto = new ReunionDto();
        reunionDto.setType(TypeReunion.SPEC);
        reunionDto.setNbrPersonEnSite(5);

        Reunion reunion = new Reunion();
        reunion.setId(1L);
        reunion.setType(TypeReunion.SPEC);
        reunion.setNbrPersonEnSite(5);

        Mockito.when(reunionMapper.dtoToReunion(reunionDto)).thenReturn(reunion);
        Mockito.when(reunionRepo.save(Mockito.any(Reunion.class))).thenReturn(reunion);

        Mockito.when(reunionRepo.getBySalleAndDateDebutGreaterThan(Mockito.any(Salle.class),Mockito.any (LocalDateTime.class))).thenReturn(new ArrayList<>());

        Mockito.when(salleRepo.findAll()).thenReturn(getListeSalles());

        // Appel de la méthode à tester
        ReunionDto result = reunionService.reserveSalle(reunionDto);

        // Vérification des résultats
        Assertions.assertThat(reunion.getSalle().getNomSalle()).isEqualTo("E2004");
    }


    //Scénario de réservation echoué lorsque le nombre des persone ne respectent pas la capacité réduite (70%) due au COVID
    @Test
    void reserveReunionSpecWhiteNbrPersonMoreThanCapacitySalleInCovid(){
        ReunionDto reunionDto = new ReunionDto();
        reunionDto.setType(TypeReunion.SPEC);
        reunionDto.setNbrPersonEnSite(8);

        Reunion reunion = new Reunion();
        reunion.setId(1L);
        reunion.setType(TypeReunion.SPEC);
        reunion.setNbrPersonEnSite(8);

        Mockito.when(reunionMapper.dtoToReunion(reunionDto)).thenReturn(reunion);



        Mockito.when(salleRepo.findAll()).thenReturn(getListeSalles());

        // Appel de la méthode à tester
        org.junit.jupiter.api.Assertions.assertThrows(ReunionNotFoundAnySalle.class,()->reunionService.reserveSalle(reunionDto));

    }




    List<Salle> getListeSalles() {
        List<Salle> salles = new ArrayList<>();


        Salle salle1 = new Salle();
        salle1.setNomSalle("E1001");
        salle1.setCapacite(23);
        salle1.setEquipements(new ArrayList<>());
        salle1.setReunions(new ArrayList<>());
        salles.add(salle1);

        Salle salle2 = new Salle();
        salle2.setNomSalle("E1002");
        salle2.setCapacite(10);
        salle2.setEquipements(Arrays.asList(Equipement.ECRAN));
        salle2.setReunions(new ArrayList<>());
        salles.add(salle2);

        Salle salle3 = new Salle();
        salle3.setNomSalle("E1003");
        salle3.setCapacite(8);
        salle3.setEquipements(Arrays.asList(Equipement.PIEUVRE));
        salle3.setReunions(new ArrayList<>());
        salles.add(salle3);

        Salle salle4 = new Salle();
        salle4.setNomSalle("E1004");
        salle4.setCapacite(4);
        salle4.setEquipements(Arrays.asList(Equipement.TABLEAU));
        salle4.setReunions(new ArrayList<>());
        salles.add(salle4);

        Salle salle5 = new Salle();
        salle5.setNomSalle("E2001");
        salle5.setCapacite(4);
        salle5.setEquipements(new ArrayList<>());
        salle5.setReunions(new ArrayList<>());
        salles.add(salle5);

        Salle salle6 = new Salle();
        salle6.setNomSalle("E2002");
        salle6.setCapacite(15);
        salle6.setEquipements(Arrays.asList(Equipement.ECRAN, Equipement.WEBCAM));
        salle6.setReunions(new ArrayList<>());
        salles.add(salle6);

        Salle salle7 = new Salle();
        salle7.setNomSalle("E2003");
        salle7.setCapacite(7);
        salle7.setEquipements(new ArrayList<>());
        salle7.setReunions(new ArrayList<>());
        salles.add(salle7);

        Salle salle8 = new Salle();
        salle8.setNomSalle("E2004");
        salle8.setCapacite(9);
        salle8.setEquipements(Arrays.asList(Equipement.TABLEAU));
        salle8.setReunions(new ArrayList<>());
        salles.add(salle8);

        Salle salle9 = new Salle();
        salle9.setNomSalle("E3001");
        salle9.setCapacite(13);
        salle9.setEquipements(Arrays.asList(Equipement.ECRAN, Equipement.WEBCAM, Equipement.PIEUVRE));
        salle9.setReunions(new ArrayList<>());
        salles.add(salle9);

        Salle salle10 = new Salle();
        salle10.setNomSalle("E3002");
        salle10.setCapacite(8);
        salle10.setEquipements(new ArrayList<>());
        salle10.setReunions(new ArrayList<>());
        salles.add(salle10);

        Salle salle11 = new Salle();
        salle11.setNomSalle("E3003");
        salle11.setCapacite(9);
        salle11.setEquipements(Arrays.asList(Equipement.ECRAN, Equipement.PIEUVRE));
        salle11.setReunions(new ArrayList<>());
        salles.add(salle11);

        Salle salle12 = new Salle();
        salle12.setNomSalle("E3004");
        salle12.setCapacite(4);
        salle12.setEquipements(new ArrayList<>());
        salle12.setReunions(new ArrayList<>());
        salles.add(salle12);

        return salles;
    }
}