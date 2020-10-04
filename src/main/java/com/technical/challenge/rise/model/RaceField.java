package com.technical.challenge.rise.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "race_field")

@NamedQuery(name = "RaceField.findByRaceField", 
query = "SELECT  rf "
		+ " FROM  RaceField rf  "
		+ "JOIN rf.race r "
		+ " JOIN  r.raceMeeting rm "
		+ " WHERE  r.raceOrder = ?1 "
		+ " AND rf.saddleCloth = ?2 "
		+ " AND rf.place = ?3 "  
		+" AND rm.scheduledDate = ?4 AND rm.trackName = ?5"
		)



@NamedQuery(name = "RaceField.findDetailsByHorseId", 
query = "SELECT  rf "
		+ " FROM  RaceField rf "
		+ "JOIN  rf.horse h"
		+ " WHERE h.horseId = ?1 "
		)

@NamedQuery(name = "RaceField.findRaceDetailsByRaceCode", 
query = "SELECT  r "
		+ " FROM  RaceField rf "
		+ "JOIN rf.race r "
		+ " WHERE r.raceCode = ?1 "
		)



public class RaceField {
	@Id
	@Column(name="RACE_FIELD_ID")
	private String raceFieldId;
	
	@ManyToOne
	@JoinColumn(name="horse_id")
	private Horse horse;
	
	@ManyToOne
	@JoinColumn(name="RACE_CODE", updatable = false , insertable = false)
	private Race race;
	
	@Column(name="SADDLE_CLOTH_NUMBER")
	private Integer saddleCloth;
	
	@Column(name="PLACE")
	private Integer place;
	
	@Column(name="STATUS")
	private String status;
	
	public Race getRace() {
		return race;
	}

	public void setRace(Race race) {
		this.race = race;
	}


	public String getRaceFieldId() {
		return raceFieldId;
	}

	public void setRaceFieldId(String raceFieldId) {
		this.raceFieldId = raceFieldId;
	}



	public Integer getSaddleCloth() {
		return saddleCloth;
	}

	public void setSaddleCloth(Integer saddleCloth) {
		this.saddleCloth = saddleCloth;
	}

	public Integer getPlace() {
		return place;
	}

	public void setPlace(Integer place) {
		this.place = place;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Horse getHorse() {
		return horse;
	}

	public void setHorse(Horse horse) {
		this.horse = horse;
	}
	public RaceField() {}
	public RaceField(String raceFieldId, String horseId, String raceId, Integer saddleCloth, Integer place, String status) {
		super();
		this.raceFieldId = raceFieldId;
		this.horse = new Horse(horseId);
		this.race = new Race(raceId);
		this.saddleCloth = saddleCloth;
		this.place = place;
		this.status = status;
		
	}
	
	
	
	
	
}
