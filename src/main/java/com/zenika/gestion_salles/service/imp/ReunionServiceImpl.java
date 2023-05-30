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
import com.zenika.gestion_salles.service.ReunionService;
import com.zenika.gestion_salles.utils.ReservationInfo;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.*;

@Service
@RequiredArgsConstructor

public class ReunionServiceImpl implements ReunionService {

    private final ReunionRepo reunionRepo;

    private final SalleRepo salleRepo;

    private final ReunionMapper reunionMapper;

    Logger logger = LoggerFactory.getLogger(ReunionServiceImpl.class);


    @Override
    public ReunionDto reserveSalle(ReunionDto reunionDto) {

        Reunion reunion=this.searchPerfectReunion(reunionDto);

        return reunionMapper.reunionToDto(reunionRepo.save(reunion));
    }
    @Override
    public void cancelReunion(long reunionId) {

        reunionRepo.deleteById(reunionId);
    }

    @Override
    public ReunionDto updateReunion(long reunionId,ReunionDto dto) {

        cancelReunion(reunionId);

        return reserveSalle(dto);
    }

    @Override
    public Reunion searchPerfectReunion(ReunionDto reunionDto) {

        Reunion reunion=reunionMapper.dtoToReunion(reunionDto);
        LocalDateTime now=getDateDuringReserve();
        List<Salle> salles=salleRepo.findAll();


        List<Salle> sallesPerfect = getSallesRespectConditionReunion(reunion,salles);
        if(sallesPerfect.isEmpty()) throw new ReunionNotFoundAnySalle("no salle disponible for this reunion");


        List<Reunion> reunions = getBestSalleForReunion(reunion,sallesPerfect,now);

        reunions.sort(Comparator.comparing(Reunion::getDateDebut));
        Iterator<Reunion> iterator = reunions.iterator();
        Reunion reunionCourante ;
        Reunion reunionSuivante ;
        if (reunions.isEmpty()){
            logger.info("salle is empty");
            return reserveReunionDate(reunion, now.plusHours(1).withMinute(0));
        }
        //deja verifier si il exist le premier
        reunionCourante = iterator.next();
        LocalDateTime courantDate = reunionCourante.getDateFin();
        //reserve  before first reunion
        if (insertReunionBetweenTwoDate(reunion,now,courantDate)) {
            logger.info("reserve befor first reunion");
            return reunion;
        }


        while (iterator.hasNext()) {
            reunionSuivante = iterator.next();
            LocalDateTime suivantDate = reunionSuivante.getDateDebut();
            //insert reunion between two reunion
            if (insertReunionBetweenTwoDate(reunion,courantDate, suivantDate)) {
                logger.info("reserve between two reunion");
                return reunion;
            }

            reunionCourante = reunionSuivante;
            }
                //insert la reunion a la fin
            insertReunionAfterDate(reunion,reunionCourante.getDateFin());
            logger.info("reserve after last reunion");
            return reunion;

    }

    @Override
    public List<ReunionDto> getReunionsBySalle(String nomSalle) {
        return salleRepo.findByNomSalle(nomSalle).orElseThrow(RuntimeException::new)
                .getReunions().stream().sorted(Comparator.comparing(Reunion::getDateDebut))
                         .map(reunionMapper::reunionToDto).toList();
    }

    void insertReunionAfterDate(Reunion reunion,LocalDateTime date){
        if(date.getHour()+ReservationInfo.DELAIS_REUNION_HEURE+ReservationInfo.DELAIS_AVANT_REUNION_VIDE<=20 ){
            reserveReunionDate(reunion,date.plusHours(ReservationInfo.DELAIS_AVANT_REUNION_VIDE));

        }else {
            DayOfWeek dayOfWeek=date.plusDays(1).getDayOfWeek();
            reserveReunionDate(reunion,date.plusDays(dayOfWeek==DayOfWeek.SATURDAY?1 : 3).withHour(8));
        }

    }

    public boolean insertReunionBetweenTwoDate(Reunion reunion,LocalDateTime date1,LocalDateTime date2){

        if(Duration.between(date1,date2).toHours()<ReservationInfo.DELAIS_REUNION_HEURE+2*ReservationInfo.DELAIS_AVANT_REUNION_VIDE) return false;


        if(date1.getHour()+ReservationInfo.DELAIS_REUNION_HEURE+ReservationInfo.DELAIS_AVANT_REUNION_VIDE<=20 ){
            reserveReunionDate(reunion,date1.plusHours(ReservationInfo.DELAIS_AVANT_REUNION_VIDE));
            return true;
        }

        DayOfWeek dayOfWeek=date1.plusDays(1).getDayOfWeek();
        LocalDateTime dateNextDay=date1.plusDays(dayOfWeek==DayOfWeek.SATURDAY?1 : 3).withHour(8);
        if(Duration.between(dateNextDay,
                date2).toHours()>=ReservationInfo.DELAIS_REUNION_HEURE+ReservationInfo.DELAIS_AVANT_REUNION_VIDE) {

            reserveReunionDate(reunion,dateNextDay);
            return true;
        }

        return false;
    }

    private  Reunion reserveReunionDate(Reunion reunion,LocalDateTime dateDebut){

        reunion.setDateDebut(dateDebut);
        reunion.setDateFin(dateDebut.plusHours(ReservationInfo.DELAIS_REUNION_HEURE));

        return reunion;
    }

    boolean isSalleHasEquipementReunion(List<Equipement> equipementsReunion,
                                      List<Equipement> equipemeentSalle){

        for(Equipement equipement: equipementsReunion)
            if(!equipemeentSalle.contains(equipement))
                return false;

        return true;
    }

    boolean isSalleRespectConditionReunion(Salle salle,Reunion reunion){

        if(reunion.getType()==TypeReunion.RS){
            if(salle.getCapacite()<3)
                return false;
        }else {

            if(!isSalleHasEquipementReunion(reunion.getType().getEquipement(),salle.getEquipements()))
                return false;

        }



        return (reunion.getNbrPersonEnSite()<=salle.getCapacite()*0.7);

    }

    LocalDateTime getDateDuringReserve() {

        LocalDateTime localDateTime = LocalDateTime.now();
        DayOfWeek dayOfWeek = localDateTime.getDayOfWeek();

        int nbrDayAdd=0;

        if (dayOfWeek == DayOfWeek.SATURDAY) nbrDayAdd=1;
        else if (dayOfWeek == DayOfWeek.SUNDAY) nbrDayAdd=2;


        //suppose 8-9 delay reservation
        return localDateTime.plusDays(nbrDayAdd).withHour(8).withMinute(0);
    }

    List<Salle> getSallesRespectConditionReunion(Reunion reunion,List<Salle> salles){

        return  salles.stream().filter(sale->this.isSalleRespectConditionReunion(sale,reunion)).toList();
    }
    List<Reunion> getBestSalleForReunion(Reunion reunion ,List<Salle> sallesPerfect,LocalDateTime date){


       Salle salle= sallesPerfect.stream().min(Comparator.comparing(s-> reunionRepo.countBySalleAndDateDebutGreaterThan(s, date))
       ).orElseThrow();

        reunion.setSalle(salle);


        return  reunionRepo.getBySalleAndDateDebutGreaterThan(salle,date);
    }

}
