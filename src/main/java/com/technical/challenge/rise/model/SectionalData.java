package com.technical.challenge.rise.model;

import java.time.LocalTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SectionalData")


@NamedQuery(name = "SectionalData.findSpeedDetailsByHorse", 
query = "SELECT  h.horseId, h.name, sd.totalTime , r.distance "
		+ " FROM  SectionalData  sd "
		+ "JOIN  sd.raceField rf "
		+ "JOIN  rf.race r "
		+ "JOIN  rf.horse h "
		)

@NamedQuery(name = "SectionalData.findFastest800THorse", 
query = "SELECT  min(sd.eightHundredTime),  h.name , h.horseId "
		+ " FROM  SectionalData  sd "
		+ "JOIN  sd.raceField rf "
		+ "JOIN  rf.horse h "
		+ " Group by name order by 1 ASC "
		)
@NamedQuery(name = "SectionalData.findFastest400THorse", 
query = "SELECT  min(sd.fourHundredTime),  h.name , h.horseId "
		+ " FROM  SectionalData  sd "
		+ "JOIN  sd.raceField rf "
		+ "JOIN  rf.horse h "
		+ " Group by name order by 1 ASC "
		)

public class SectionalData {
	
	@Id
	@GeneratedValue
	@Column(name ="sectionid")
	private Integer sectionId;
	
	@OneToOne
	@JoinColumn(name = "race_field_id")
	private RaceField raceField;
	

	@Column(name ="DATE")
	private Date date;
	
	@Column(name ="LOCATION")
	private String location;
	
	
	@Column(name ="RACE_NO")
	private Integer raceNo;
	
	@Column(name ="TABNO")
	private Integer tabNo;
	
	@Column(name ="HORSE")
	private String horse;
	
		
	@Column(name ="PLACE")
	private Integer place;
	
	@Column(name ="MARGIN")
	private Double margin;
	
	@Column(name ="EightHunMar")
	private Double eightHundredmargin;
	
	@Column(name ="FourHunMar")
	private Double fourHundredmargin;
	
	@Column(name ="EightHunTime")
	private Double eightHundredTime;
	
	@Column(name ="FourHunTime")
	private Double fourHundredTime;
	
	@Column(name ="EightHunWidth")
	private Integer eightHunWidth;
	
	@Column(name ="FourHunWidth")
	private Integer fourHunWidth;
	
	
	
	
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getEightHundredmargin() {
		return eightHundredmargin;
	}

	public void setEightHundredmargin(Double eightHundredmargin) {
		this.eightHundredmargin = eightHundredmargin;
	}

	@Column(name ="TIME")
	private LocalTime totalTime;
		
	
	public Integer getSectionId() {
		return sectionId;
	}

	public void setSectionId(Integer sectionId) {
		this.sectionId = sectionId;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Integer getRaceNo() {
		return raceNo;
	}

	public void setRaceNo(Integer raceNo) {
		this.raceNo = raceNo;
	}

	public Integer getTabNo() {
		return tabNo;
	}

	public void setTabNo(Integer tabNo) {
		this.tabNo = tabNo;
	}

	public String getHorse() {
		return horse;
	}

	public void setHorse(String horse) {
		this.horse = horse;
	}


	public Integer getPlace() {
		return place;
	}

	public void setPlace(Integer place) {
		this.place = place;
	}

	public Double getMargin() {
		return margin;
	}

	public void setMargin(Double margin) {
		this.margin = margin;
	}

	
	public LocalTime getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(LocalTime totalTime) {
		this.totalTime = totalTime;
	}

	public SectionalData() {}



	public Double getFourHundredmargin() {
		return fourHundredmargin;
	}

	public void setFourHundredmargin(Double fourHundredmargin) {
		this.fourHundredmargin = fourHundredmargin;
	}

	public Double getEightHundredTime() {
		return eightHundredTime;
	}

	public void setEightHundredTime(Double eightHundredTime) {
		this.eightHundredTime = eightHundredTime;
	}

	public Double getFourHundredTime() {
		return fourHundredTime;
	}

	public void setFourHundredTime(Double fourHundredTime) {
		this.fourHundredTime = fourHundredTime;
	}

	public Integer getEightHunWidth() {
		return eightHunWidth;
	}

	public void setEightHunWidth(Integer eightHunWidth) {
		this.eightHunWidth = eightHunWidth;
	}

	public Integer getFourHunWidth() {
		return fourHunWidth;
	}

	public void setFourHunWidth(Integer fourHunWidth) {
		this.fourHunWidth = fourHunWidth;
	}


	public RaceField getRaceField() {
		return raceField;
	}

	public void setRaceField(RaceField raceField) {
		this.raceField = raceField;
	}

	public SectionalData(Date date, String location, Integer raceNo, Integer tabNo, String horse, 
			Integer place, Double margin, Double eightHundredmargin, Double fourHundredmargin, Double eightHundredTime,
			Double fourHundredTime, Integer eightHunWidth, Integer fourHunWidth, LocalTime totalTime) {
		super();
		this.date = date;
		this.location = location;
		this.raceNo = raceNo;
		this.tabNo = tabNo;
		this.horse = horse;
		this.place = place;
		this.margin = margin;
		this.eightHundredmargin = eightHundredmargin;
		this.fourHundredmargin = fourHundredmargin;
		this.eightHundredTime = eightHundredTime;
		this.fourHundredTime = fourHundredTime;
		this.eightHunWidth = eightHunWidth;
		this.fourHunWidth = fourHunWidth;
		this.totalTime = totalTime;
	}
	

}
