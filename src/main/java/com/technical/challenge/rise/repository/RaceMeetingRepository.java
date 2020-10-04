package com.technical.challenge.rise.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.technical.challenge.rise.model.RaceMeeting;



@Repository
public interface RaceMeetingRepository extends JpaRepository<RaceMeeting, String>{
	
	
	List < RaceMeeting > findByDateAndTrack(Date schduleDate,String trackName);
	
	
}
