package com.technical.challenge.rise.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.technical.challenge.rise.model.Race;



@Repository
public interface RaceRepository extends JpaRepository<Race, String>{
	
	
	List < Race> findByRaceMeetingCode(Date schduleDate,String trackName);
}
