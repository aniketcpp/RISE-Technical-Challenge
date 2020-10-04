package com.technical.challenge.rise.model;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "race")
@NamedQuery(name = "Race.findByRaceMeetingCode", 
query = "SELECT r " + 
		"FROM Race r JOIN r.raceMeeting rm Where  "
		+ " rm.scheduledDate = ?1 AND rm.trackName = ?2  ")



public class Race {
	@Id
	@Column(name="RACE_CODE")
	private String raceCode;
	
	@Column(name="name")
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "RACE_MEETING_CODE") 
	private RaceMeeting raceMeeting;
	 
		
	@Column(name="DISTANCE")
	private Integer distance;
	//To do

	@Column(name="SCHEDULED_DATE")
	private Date scheduledDateTime;
	
	@Column(name="RACE_ORDER")
	private Integer raceOrder;

	public String getRaceCode() {
		return raceCode;
	}


	public void setRaceCode(String raceCode) {
		this.raceCode = raceCode;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public RaceMeeting getRaceMeeting() {
		return raceMeeting;
	}


	public void setRaceMeeting(RaceMeeting raceMeeting) {
		this.raceMeeting = raceMeeting;
	}


	public Date getScheduledDateTime() {
		return scheduledDateTime;
	}


	public void setScheduledDateTime(Date scheduledDateTime) {
		this.scheduledDateTime = scheduledDateTime;
	}


	public Integer getRaceOrder() {
		return raceOrder;
	}


	public void setRaceOrder(Integer raceOrder) {
		this.raceOrder = raceOrder;
	}


	public Integer getDistance() {
		return distance;
	}


	public void setDistance(Integer distance) {
		this.distance = distance;
	}



	public Race(String raceCode, String name, String raceMeeting, Date scheduledDateTime, Integer raceOrder,
			Integer distance) {
		super();
		this.raceCode = raceCode;
		this.name = name;
		this.raceMeeting = new RaceMeeting(raceMeeting);
		this.scheduledDateTime = scheduledDateTime;
		this.raceOrder = raceOrder;
		this.distance = distance;
	}

	public Race(String raceCode) {
		this.raceCode= raceCode;
	}
	

	
	
	public Race() {}

	

	
	
	
}
