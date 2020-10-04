package com.technical.challenge.rise.service;

import java.text.DecimalFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.OptionalDouble;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.technical.challenge.rise.exception.AggregatesNotFoundException;
import com.technical.challenge.rise.model.AverageDetails;
import com.technical.challenge.rise.model.HorseDetails;
import com.technical.challenge.rise.model.SectionalData;
import com.technical.challenge.rise.repository.SectionalDataRepository;

@Service
public class HorseService {


	private static final  Integer Section800 =800;
	private static final  Integer Section400 =400;
	
	@Autowired
	private SectionalDataRepository  sectionalDataRepository;

	
	DecimalFormat df = new DecimalFormat("#.###");
	
	public Double getOverAllAverage800MarginOfHorse() {
		
		List<SectionalData> list = sectionalDataRepository.findAll();
		
		OptionalDouble s =	list.stream().mapToDouble(SectionalData :: getEightHundredmargin).average();
		if(!s.isPresent()) {
			throw new AggregatesNotFoundException("Couldn't calculate Average 800Margin");
			}	
		
		return s.getAsDouble();

	
	}
	
	public Double getOverAllAverage400MarginOfHorse() {
		
		List<SectionalData> list = sectionalDataRepository.findAll();
		
		OptionalDouble s =	list.stream().mapToDouble(SectionalData :: getFourHundredmargin).average();
		
		if(!s.isPresent()) {
			throw new AggregatesNotFoundException("Couldn't calculate Average 400Margin");
			}	
		
		return s.getAsDouble();

	}
	
	public Double getOverAllAverageMarginOfHorse() {
		
		List<SectionalData> list = sectionalDataRepository.findAll();
		
		OptionalDouble s =	list.stream().mapToDouble(SectionalData :: getMargin).average();
		if(!s.isPresent()) {
			throw new AggregatesNotFoundException("Couldn't calculate Average Margin");
			}	
		
		return s.getAsDouble();

	}
	
	public Double getOverAllAverage800WidthOfHorse() {
		
		List<SectionalData> list = sectionalDataRepository.findAll();
		
		OptionalDouble s =	list.stream().mapToDouble(SectionalData :: getEightHunWidth).average();
		if(!s.isPresent()) {
			throw new AggregatesNotFoundException("Couldn't calculate Average 800Width");
			}	
		
		return s.getAsDouble();

	}
	
	public Double  getOverAllAverage400WidthOfHorse() {
		
		List<SectionalData> list = sectionalDataRepository.findAll();
		
		OptionalDouble s =	list.stream().mapToDouble(SectionalData :: getFourHunWidth).average();
		
		if(!s.isPresent()) {
			throw new AggregatesNotFoundException("Couldn't calculate Average 400Width");
			}	
		
		return s.getAsDouble();
	}
	
	public Double  getOverAllAverage400TimeOfHorse() {
		
		List<SectionalData> list = sectionalDataRepository.findAll();
		
		OptionalDouble s =	list.stream().mapToDouble(SectionalData :: getFourHundredTime).average();
			
			if(!s.isPresent()) {
			throw new AggregatesNotFoundException("Couldn't calculate Average 400T");
			}
		
			return s.getAsDouble();
	}
	
public Double  getOverAllAverage800TimeOfHorse() {
		
		List<SectionalData> list = sectionalDataRepository.findAll();
		
		OptionalDouble s =	list.stream().mapToDouble(SectionalData :: getEightHundredTime).average();
		
			if(!s.isPresent()) {
			throw new AggregatesNotFoundException("Couldn't calculate Average 800T");
			}

		return s.getAsDouble();
	}

		public Double  getAverageSpeedFor800MetreSection() {
			return Section800/getOverAllAverage800TimeOfHorse();
		}
		
		public Double  getAverageSpeedFor400MetreSection() {
			
			return Section400/getOverAllAverage400TimeOfHorse();
		}

public Double  getAverageSpeedOfHorse() {
	double speed= 0;
	Integer totaDistance =0;
	
	Integer sec =0 ;
	LocalTime t = null;
		List<List<Object>> list = sectionalDataRepository.findSpeedDetailsByHorse();
		
		for(List<Object> l : list) {
			if(l.get(2) != null) {
				t = (LocalTime) l.get(2);
				sec = sec + (t.getMinute()*60+  t.getSecond());
				totaDistance+=((Integer) l.get(3)).intValue();				
			}		
		}
		if(sec !=0 ) {
			speed =(double) totaDistance/sec;
			
		}
		return speed;
	}

	
public List<HorseDetails>  getSpeedOfAllHorse() {
		
	List<HorseDetails> hList = new ArrayList<>();
		List<List<Object>> list = sectionalDataRepository.findSpeedDetailsByHorse();
		for(List<Object> l : list) {
			hList.add(horseDetailsInfo(l));
		}
		return hList;
	}


public HorseDetails  speedOfHorseByHorseId(String horseId) {
	
	HorseDetails horseDetails = new HorseDetails();
		List<List<Object>> list = sectionalDataRepository.findSpeedDetailsByHorse();
		for(List<Object> l : list) {
			if(horseId.equals((String)l.get(0))) {
				return horseDetailsInfo(l);
			}
		}
		return horseDetails;

	}

public HorseDetails horseDetailsInfo(List<Object> l) {
	double speed= 0;
	Integer sec =0 ;
	LocalTime t = null;
	HorseDetails horseDetails = new HorseDetails() ;
	horseDetails.setHorseId((String)l.get(0));
	horseDetails.setHorseName((String)l.get(1));
	if(l.get(2) != null) {
		t = (LocalTime) l.get(2);
		sec = t.getMinute()*60+  t.getSecond();
		if(sec !=0 ) {
		speed =(double) ((Integer) l.get(3)).intValue()/(t.getMinute()*60+  t.getSecond());
		horseDetails.setSpeed(df.format(speed) +" m/s");
		}
	}
	return horseDetails;

}


public HorseDetails  getFastestHorseToFinish800T() {
	List<List<Object>> list = sectionalDataRepository.findFastest800THorse();
	
	HorseDetails horseDetails = new HorseDetails();
	horseDetails.setSpeed(""+list.get(0).get(0));
	horseDetails.setHorseName(""+list.get(0).get(1));
	horseDetails.setHorseId(""+list.get(0).get(2));
	return horseDetails;
}

public HorseDetails  getFastestHorseToFinish400T() {
	List<List<Object>> list = sectionalDataRepository.findFastest400THorse();
	HorseDetails horseDetails = new HorseDetails();
	horseDetails.setSpeed(""+list.get(0).get(0));
	horseDetails.setHorseName(""+list.get(0).get(1));
	horseDetails.setHorseId(""+list.get(0).get(2));
	return horseDetails;
}


public AverageDetails consolidateAverageData() {
	AverageDetails averageDetails = new AverageDetails();
	
	averageDetails.setAvgMargin(df.format(getOverAllAverageMarginOfHorse()));
	averageDetails.setAvg400Margin(df.format(getOverAllAverage400MarginOfHorse()));
	averageDetails.setAvg800Margin(df.format(getOverAllAverage800MarginOfHorse()));
	
	
	averageDetails.setAvg400Time(df.format(getOverAllAverage400TimeOfHorse()));
	averageDetails.setAvg800Time(df.format(getOverAllAverage800TimeOfHorse()));
	
	averageDetails.setAvgSpeedofHorse(df.format(getAverageSpeedOfHorse()));
	averageDetails.setAvgSpeed400(df.format(getAverageSpeedFor400MetreSection()));
	averageDetails.setAvgSpeed800(df.format(getAverageSpeedFor800MetreSection()));
	
	return averageDetails;
	
	
}

}
