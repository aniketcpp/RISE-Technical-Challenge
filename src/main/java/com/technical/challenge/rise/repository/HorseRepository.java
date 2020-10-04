package com.technical.challenge.rise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.technical.challenge.rise.model.Horse;



@Repository
public interface HorseRepository extends JpaRepository<Horse, String>{
	
	
}
