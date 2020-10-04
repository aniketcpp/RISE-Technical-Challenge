package com.technical.challenge.rise.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.technical.challenge.rise.model.Race;
import com.technical.challenge.rise.model.RaceField;



@Repository
public interface RaceFieldRepository extends JpaRepository<RaceField, String>{
	
	List <RaceField> findByRaceField(Integer order ,Integer cloth ,Integer place, Date d , String str);
	
	List <RaceField> findDetailsByHorseId(String horseId);
	
	Race findRaceDetailsByRaceCode(String raceCode);
	
}
