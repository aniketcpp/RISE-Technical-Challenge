package com.technical.challenge.rise.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "Race_Meeting")
@NamedQuery(name = "RaceMeeting.findByDateAndTrack", query = "select r from RaceMeeting r where r.scheduledDate = ?1 AND r.trackName = ?2 ")

public class RaceMeeting {
	@Id
	@Column(name="RACE_MEETING_CODE")
	private String raceMeetingCode;
	
		
	@Column(name="SCHEDULED_DATE")
	private Date scheduledDate;
	
	@Column(name="TRACK_NAME")
	private String trackName;
	


	public String getRaceMeetingCode() {
		return raceMeetingCode;
	}

	public void setRaceMeetingCode(String raceMeetingCode) {
		this.raceMeetingCode = raceMeetingCode;
	}
	public RaceMeeting() {}
	public RaceMeeting(String raceMeetingCode) {
		this.raceMeetingCode=raceMeetingCode;
	}
	public RaceMeeting(String raceMeetingCode, Date scheduledDate, String trackName) {
		super();
		this.raceMeetingCode = raceMeetingCode;
		this.scheduledDate = scheduledDate;
		this.trackName = trackName;
	}

	public String getTrackName() {
		return trackName;
	}

	public void setTrackName(String trackName) {
		this.trackName = trackName;
	}

	public Date getScheduledDate() {
		return scheduledDate;
	}

	public void setScheduledDate(Date scheduledTime) {
		this.scheduledDate = scheduledDate;
	}

	
	
	
	
	
	
}
