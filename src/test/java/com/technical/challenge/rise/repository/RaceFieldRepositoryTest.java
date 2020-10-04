package com.technical.challenge.rise.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.technical.challenge.rise.model.Race;


@ExtendWith(SpringExtension.class)
@DataJpaTest
public class RaceFieldRepositoryTest {
	
	@Autowired
	private RaceFieldRepository raceFieldRepository;
	
@Test
public void testfindRaceDetailsByRaceCode() {
	Race r = raceFieldRepository.findRaceDetailsByRaceCode("APC01092014");
	assertEquals(2138, r.getDistance());
	
}
	
	

}

