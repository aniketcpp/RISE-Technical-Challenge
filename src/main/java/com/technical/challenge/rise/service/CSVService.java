package com.technical.challenge.rise.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.technical.challenge.rise.model.RaceField;
import com.technical.challenge.rise.model.SectionalData;
import com.technical.challenge.rise.model.ValidationMessages;
import com.technical.challenge.rise.repository.RaceFieldRepository;
import com.technical.challenge.rise.repository.SectionalDataRepository;

@Service
public class CSVService {
  @Autowired
  SectionalDataRepository repository;
  
   public static String TYPE = "text/csv";

	@Autowired
	private RaceFieldRepository raceFieldRepository;

	 ValidationMessages validationMessages  ;

  public  boolean hasCSVFormat(MultipartFile file) {

    if (TYPE.equals(file.getContentType())
    		|| file.getContentType().equals("application/vnd.ms-excel")) {
      return true;
    }

    return false;
  }

  public ValidationMessages save(MultipartFile file) throws NumberFormatException, ParseException {
	
    try {
      List<SectionalData> tutorials = readAndValidate(file.getInputStream());
      repository.saveAll(tutorials);
         
    } catch (IOException e) {
      throw new RuntimeException("fail to store csv data: " + e.getMessage());
    }
    return validationMessages;
  }

  public  List<SectionalData> readAndValidate(InputStream is) throws NumberFormatException, ParseException {
	  validationMessages  = new ValidationMessages();
	   List<String> errorMessages = new ArrayList<String>();
	  
	    try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
	        CSVParser csvParser = new CSVParser(fileReader,
	            CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

	      List<SectionalData> sectionanDataList = new ArrayList<>();
	      int numberOfRecords=0;
	      Iterable<CSVRecord> csvRecords = csvParser.getRecords();
	      
	  // To Do : Use IO Stream instead of fully reading file at once. 
	      
	      for (CSVRecord csvRecord : csvRecords) {
	    	  SectionalData sectionalData = constructRow( csvRecord) ;
	    	   RaceField raceField = validateAndGetRaceField(sectionalData);
					  if(raceField != null )  {
						  sectionalData.setRaceField(raceField);
						  sectionanDataList.add(sectionalData);
					  }else {
						  errorMessages.add("Record# : " +( numberOfRecords+1) +" has been failed to load. The data given for Location " + sectionalData.getLocation()
						   +" and horse name "+ sectionalData.getHorse() + " is inconsistent with existing DB records") ;
					  } 	
					  numberOfRecords++; 	 
	      }
	    	  
	      validationMessages.setErrorList(errorMessages);
	      validationMessages.setTotalNumberOfRecords(numberOfRecords);
	      validationMessages.setSuccessfulRecords(sectionanDataList.size());
	      return sectionanDataList;
	    } catch (IOException e) {
	      throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
	    }
	  }

	  public  RaceField validateAndGetRaceField(SectionalData sectionalData) throws ParseException {
	  
		  SimpleDateFormat formatter = new SimpleDateFormat("yyy-M-dd", Locale.ENGLISH);
		  List<RaceField> allRaceFieldData = raceFieldRepository.findByRaceField( 
				  sectionalData.getRaceNo(),
				  sectionalData.getTabNo(),
				  sectionalData.getPlace(),
				  sectionalData.getDate(),
				  sectionalData.getLocation().toUpperCase());
		  
			if(allRaceFieldData.size() == 1 ) {
				return allRaceFieldData.get(0);
			}
		return null;
	  }
	 


  private SectionalData constructRow(CSVRecord csvRecord) throws NumberFormatException, ParseException {
	  
	  SimpleDateFormat formatter = new SimpleDateFormat("yyy-M-dd", Locale.ENGLISH);
	  SectionalData sectionalData = new SectionalData(
  			
			  formatter.parse(csvRecord.get("Date")),
			  csvRecord.get("Location"),
			  Integer.valueOf(csvRecord.get("Race No")),
			  Integer.valueOf(csvRecord.get("TAB No")),
			  csvRecord.get("Horse"),
			  Integer.valueOf(csvRecord.get("Place")),
			  
			  Double.valueOf(csvRecord.get("Margin")),
			  Double.valueOf(csvRecord.get("EightHunMar")),
			  Double.valueOf(csvRecord.get("FourHunMar")),
			  
			  Double.valueOf(csvRecord.get("EightHunTime")),
			  Double.valueOf(csvRecord.get("FourHunTime")),
			  
			  Integer.valueOf(csvRecord.get("EightHunWidth")),
			  Integer.valueOf(csvRecord.get("FourHunWidth")),
			  
			  
			  LocalTime.of(0, 
					  Integer.valueOf(csvRecord.get("Time").substring(0, 1)), 
					  Integer.valueOf(csvRecord.get("Time").substring(2, 4)), 
					  Integer.valueOf(csvRecord.get("Time").substring(5, 6)))
			 );
	return sectionalData;
  }
}

