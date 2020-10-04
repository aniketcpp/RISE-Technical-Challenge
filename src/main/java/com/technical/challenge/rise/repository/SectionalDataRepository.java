package com.technical.challenge.rise.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.technical.challenge.rise.model.SectionalData;

@Repository
public interface SectionalDataRepository extends JpaRepository<SectionalData, Integer>{

	List<List<Object>> findSpeedDetailsByHorse();
	
	List<List<Object>>  findFastest800THorse();
	
	List<List<Object>> findFastest400THorse();
}
