package com.technical.challenge.rise.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.technical.challenge.rise.model.AverageDetails;
import com.technical.challenge.rise.model.HorseDetails;
import com.technical.challenge.rise.model.ValidationMessages;
import com.technical.challenge.rise.service.CSVService;
import com.technical.challenge.rise.service.HorseService;
import com.technical.challenge.rise.service.ResponseMessage;

@RestController
public class RiseController {
		 
	 @Autowired private HorseService horseService;
	 @Autowired private CSVService fileService;
	 
	 @GetMapping("/rise/speed/all")
	 public List<HorseDetails> speedOfAllHorses() {
		 
		 return horseService.getSpeedOfAllHorse();
	 }
	 
	 
	 @GetMapping("/rise/speed/{horseId}")
	 public HorseDetails speedOfHorseByHorseId(@PathVariable String horseId) {
		 
		 return horseService.speedOfHorseByHorseId( horseId);
	 }
	 
	 @GetMapping("/rise/speed/fast800")
	 public HorseDetails getFastestHorsesof800T() {
		 return horseService.getFastestHorseToFinish800T();
	 }
	 
	 @GetMapping("/rise/speed/fast400")
	 public HorseDetails getFastestHorsesof400T() {
		 return horseService.getFastestHorseToFinish400T();
	 }
	 
	 
	 @GetMapping("/rise/agg")
	 public AverageDetails averageDetails() {
		 
		 return horseService.consolidateAverageData();
	 }
	 

	@PostMapping("/rise/upload")
	public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
		String message = "";
		if (fileService.hasCSVFormat(file)) {
			try {
				
				ValidationMessages	validationMessages = fileService.save(file);
				ResponseMessage responseMessage = getResponseMessage(validationMessages);
				
				return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
			} catch (Exception e) {
				message = "Could not upload the file: " + file.getOriginalFilename() + "!";
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
			}
		}

		message = "Please upload a csv file!";
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
	}
	
public ResponseMessage getResponseMessage(ValidationMessages validationMessages ) {
	
	ResponseMessage responseMessage ;
	String message="";
	
	if(validationMessages.getSuccessfulRecords().equals(validationMessages.getTotalNumberOfRecords())){
		message = "Uploaded All " +validationMessages.getSuccessfulRecords() + " record(s) successfully:";
		responseMessage = new ResponseMessage(message);
	}else {
		message = "Uploaded " +validationMessages.getSuccessfulRecords() + " record(s) successfully:\n";
		message =message + "There are " +(validationMessages.getTotalNumberOfRecords() - validationMessages.getSuccessfulRecords() ) + " Failed Record(s) not loaded";
		responseMessage = new ResponseMessage(message+validationMessages.getErrorList().toString());
	}
	
	
	
	return responseMessage;
	
}

}
