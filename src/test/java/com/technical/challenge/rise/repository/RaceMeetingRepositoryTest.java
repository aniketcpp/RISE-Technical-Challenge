package com.technical.challenge.rise.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.technical.challenge.rise.model.Race;
import com.technical.challenge.rise.model.RaceField;
import com.technical.challenge.rise.model.RaceMeeting;


@ExtendWith(SpringExtension.class)
@DataJpaTest
public class RaceMeetingRepositoryTest {
	
	@Autowired
	private RaceMeetingRepository raceMeetingRepository;
	
	@Autowired
	private RaceRepository raceRepository;
	
	@Autowired
	private RaceFieldRepository raceFieldRepository;
	
	@Test
	public void testFindRaceField() throws ParseException {
		List<RaceField> allAccounts;
		SimpleDateFormat formatter = new SimpleDateFormat("yyy-M-dd", Locale.ENGLISH);
			allAccounts = raceFieldRepository.findByRaceField(1,3, 2,formatter.parse("2020-09-01"), "ALBION PARK");
			assertEquals(1,allAccounts.size());
	
		
		
	}
	
	
	
	
	@Test
	public void testFindAll() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyy-M-dd", Locale.ENGLISH);
		
		List<RaceMeeting> allAccounts;
		try {
			allAccounts = raceMeetingRepository.findByDateAndTrack(formatter.parse("2020-09-01"), "ALBION PARK");
			assertThat(allAccounts.size() == 2);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	

	@Test
	public void testFindRace() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyy-M-dd", Locale.ENGLISH);
		
		List<Race> allAccounts;
		try {
			allAccounts = raceRepository.findByRaceMeetingCode(formatter.parse("2020-09-01"), "ALBION PARK");
			assertEquals(8,allAccounts.size());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	

}

