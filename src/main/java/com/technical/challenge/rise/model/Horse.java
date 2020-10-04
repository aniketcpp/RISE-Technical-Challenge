package com.technical.challenge.rise.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "horse")

public class Horse {
	@Id
	@Column(name="horse_id")
	private String horseId;
	
	
	@Column(name="name")
	private String name;




	public String getHorseId() {
		return horseId;
	}


	public void setHorseId(String horseId) {
		this.horseId = horseId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	public Horse() {}
	public Horse(String horseId) {
		this.horseId= horseId;
	}

	public Horse(String horseId, String name) {
		super();
		this.horseId = horseId;
		this.name = name;
	}

	
	
	
	
}
