package com.technical.challenge.rise.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.technical.challenge.rise.exception.AggregatesNotFoundException;
import com.technical.challenge.rise.model.Horse;
import com.technical.challenge.rise.model.HorseDetails;
import com.technical.challenge.rise.model.Race;
import com.technical.challenge.rise.model.RaceField;
import com.technical.challenge.rise.model.SectionalData;
import com.technical.challenge.rise.repository.SectionalDataRepository;

public class HorseServiceTest {

	static List<SectionalData> testData = new ArrayList<>();
	static List<List<Object>> obj = new ArrayList<>();
	static List<List<Object>> objTest = new ArrayList<>();
	  @Mock
	  private SectionalDataRepository  sectionalDataRepository;
	 
	  @InjectMocks
	  private HorseService horseService;
	  
	  @BeforeEach
	    void setup(){
	        MockitoAnnotations.initMocks(this);
	        
	    	Horse h = new Horse();
			h.setHorseId("123");
			h.setName("Alex");
			
			Race race = new Race();
			race.setRaceCode("1");
			race.setDistance(1200);
			
			
			RaceField raceField = new RaceField();
			raceField.setHorse(h);
			raceField.setRace(race);
			List<RaceField> r = new ArrayList<>();	
			r.add(raceField);
			
			List<Object> obj1 = new ArrayList<>();
			obj1.add("123");
			obj1.add("Test");
			obj1.add(LocalTime.of(0,2,30));
	        obj1.add(1200);
	        obj.add(obj1);
	        
	        List<Object>  obj2 = new ArrayList<>();
			obj2.add("345");
			obj2.add("Test1");
			obj2.add(LocalTime.of(0,1,30));
	        obj2.add(1200);
	        obj.add(obj2);
	        
	        
	        List<Object> obj3 = new ArrayList<>();
	        obj3.add("50.0");
	        obj3.add("Horse");
	        obj3.add("345");
	        objTest.add(obj3);
	        
	        List<Object> obj4 = new ArrayList<>();
	        obj4.add("20.0");
	        obj4.add("Horse1");
	        obj3.add("89");
	        objTest.add(obj4);
	        
	        
	        
	        SectionalData sectionalData = new SectionalData();
			sectionalData.setEightHundredmargin(15.0);
			sectionalData.setFourHundredmargin(11.0);
			sectionalData.setMargin(5.0);
			sectionalData.setEightHunWidth(20);
			sectionalData.setFourHunWidth(20);
			sectionalData.setFourHundredTime(28.0);
			sectionalData.setEightHundredTime(61.0);
			sectionalData.setRaceField(raceField);
			sectionalData.setTotalTime(LocalTime.of(0,2, 1));
			testData.add(sectionalData);
			sectionalData = new SectionalData();
			sectionalData.setEightHundredmargin(12.0);
			sectionalData.setFourHundredmargin(8.0);
			sectionalData.setMargin(4.0);
			sectionalData.setEightHunWidth(15);
			sectionalData.setFourHunWidth(18);
			sectionalData.setFourHundredTime(29.0);
			sectionalData.setEightHundredTime(58.0);
			
			testData.add(sectionalData);
			
		
	    }

	 
	
	@Test
	public void testGetOverAllAverage800MarginOfHorse() throws Exception {
		when(sectionalDataRepository.findAll()).thenReturn(testData); 
		 assertEquals(13.5, horseService.getOverAllAverage800MarginOfHorse());
	}

	@Test
	public void testGetOverAllAverage400MarginOfHorse() throws Exception {
		when(sectionalDataRepository.findAll()).thenReturn(testData); 
		 assertEquals(9.5, horseService.getOverAllAverage400MarginOfHorse());
	}

	@Test
	public void testGetOverAllAverageMarginOfHorse() throws Exception {
		when(sectionalDataRepository.findAll()).thenReturn(testData); 
		assertEquals(4.5, horseService.getOverAllAverageMarginOfHorse());
	}

	@Test
	public void testGetOverAll800WidthOfHorse() throws Exception {
		when(sectionalDataRepository.findAll()).thenReturn(testData); 
		assertEquals(17.5, horseService.getOverAllAverage800WidthOfHorse());
	}

	@Test
	public void testGetOverAll400WidthOfHorse() throws Exception {
		when(sectionalDataRepository.findAll()).thenReturn(testData); 
		assertEquals(19, horseService.getOverAllAverage400WidthOfHorse());
	}
	
	@Test
	public void testGetOverAll400TimeOfHorse() throws Exception {
		when(sectionalDataRepository.findAll()).thenReturn(testData); 
		assertEquals(28.5, horseService.getOverAllAverage400TimeOfHorse());
	}
	
	@Test
	public void testGetOverAll800TimeOfHorse() throws Exception {
		when(sectionalDataRepository.findAll()).thenReturn(testData); 
		assertEquals(59.5, horseService.getOverAllAverage800TimeOfHorse());
	}
	
	@Test
	public void testGetSpeedOfHorse() throws Exception {
		when(sectionalDataRepository.findSpeedDetailsByHorse()).thenReturn(obj); 
		List<HorseDetails> hList = horseService.getSpeedOfAllHorse();
		assertEquals("8 m/s", hList.get(0).getSpeed());
		
	}
	
	@Test
	public void testGetSpeedOfHorseById() throws Exception {
		when(sectionalDataRepository.findSpeedDetailsByHorse()).thenReturn(obj); 
		HorseDetails hList = horseService.speedOfHorseByHorseId("123");
		assertEquals("8 m/s", hList.getSpeed());
	}
	
	@Test
	public void testGetFastestHorseToFinish800T() throws Exception {
		when(sectionalDataRepository.findFastest800THorse()).thenReturn(objTest);  
		assertEquals("50.0",horseService.getFastestHorseToFinish800T().getSpeed());
	}
	
	@Test
	public void testGetFastestHorseToFinish400T() throws Exception {
		when(sectionalDataRepository.findFastest400THorse()).thenReturn(objTest);  
		assertEquals("50.0",horseService.getFastestHorseToFinish400T().getSpeed());
	}
	
	
	
	
	
	
	
	
	
	
	
	 @Test
		public void testGetOverAllAverage800MarginOfHorseNoRecords() throws Exception {
		  testData= new ArrayList<>();
			when(sectionalDataRepository.findAll()).thenReturn(testData); 

			Assertions.assertThrows(AggregatesNotFoundException.class, () -> {
				horseService.getOverAllAverage800MarginOfHorse();
			  });
			
	}
	 @Test
		public void testGetOverAllAverage400MarginOfHorseNoRecords() throws Exception {
		  testData= new ArrayList<>();
			when(sectionalDataRepository.findAll()).thenReturn(testData); 
		
			Assertions.assertThrows(AggregatesNotFoundException.class, () -> {
				horseService.getOverAllAverage400MarginOfHorse();
			  });
			
		}

		@Test
		public void testGetOverAllAverageMarginOfHorseNoRecords() throws Exception {
			  testData= new ArrayList<>();
			when(sectionalDataRepository.findAll()).thenReturn(testData); 
			Assertions.assertThrows(AggregatesNotFoundException.class, () -> {
				horseService.getOverAllAverageMarginOfHorse();
			  });
			
		}

		@Test
		public void testGetOverAll800WidthOfHorseNoRecords() throws Exception {
			  testData= new ArrayList<>();
			when(sectionalDataRepository.findAll()).thenReturn(testData); 
				Assertions.assertThrows(AggregatesNotFoundException.class, () -> {
				horseService.getOverAllAverage800WidthOfHorse();
			  });
		
			
		}

		@Test
		public void testGetOverAll400WidthOfHorseNoRecords() throws Exception {
			  testData= new ArrayList<>();
			when(sectionalDataRepository.findAll()).thenReturn(testData); 
			Assertions.assertThrows(AggregatesNotFoundException.class, () -> {
				horseService.getOverAllAverage400WidthOfHorse();
			  });
		
	}
		
		@Test
		public void testGetOverAll400TimeOfHorseNoRecords() throws Exception {
			 testData= new ArrayList<>();
			when(sectionalDataRepository.findAll()).thenReturn(testData); 
			Assertions.assertThrows(AggregatesNotFoundException.class, () -> {
				horseService.getOverAllAverage400TimeOfHorse();
			  });
			
		}
		
		@Test
		public void testGetOverAll800TimeOfHorseNoRecords() throws Exception {
			 testData= new ArrayList<>();
			when(sectionalDataRepository.findAll()).thenReturn(testData); 
			Assertions.assertThrows(AggregatesNotFoundException.class, () -> {
				horseService.getOverAllAverage800TimeOfHorse();
			  });
		
		}
		
}
