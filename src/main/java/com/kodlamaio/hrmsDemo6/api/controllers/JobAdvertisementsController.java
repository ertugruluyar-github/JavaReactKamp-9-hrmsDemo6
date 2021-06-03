package com.kodlamaio.hrmsDemo6.api.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.hrmsDemo6.business.abstracts.JobAdvertisementService;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.ErrorDataResult;
import com.kodlamaio.hrmsDemo6.entities.concretes.JobAdvertisement;

@RestController
@RequestMapping("/api/jobadvertisements")
public class JobAdvertisementsController {
	
	private JobAdvertisementService jobAdvertisementService;
	
	@Autowired
	public JobAdvertisementsController(JobAdvertisementService jobAdvertisementService) {
		this.jobAdvertisementService = jobAdvertisementService;
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> add(@Valid @RequestBody JobAdvertisement jobAdvertisement) {
		return ResponseEntity.ok(this.jobAdvertisementService.add(jobAdvertisement));
	}
	
	@GetMapping("/getallactivated")
	public ResponseEntity<?> getAllActivated() {
		return ResponseEntity.ok(this.jobAdvertisementService.getByActivated());
	}
	
	@GetMapping("/getallactivatedorderbyreleasedateasc")
	public ResponseEntity<?> getByActivatedOrderByReleaseDateAsc() {
		return ResponseEntity.ok(this.jobAdvertisementService.getByActivatedOrderByReleaseDateAsc());
	}	

	@GetMapping("/getallactivatedorderbyapplicationdeadlineasc")
	public ResponseEntity<?> getByActivatedOrderByApplicationDeadlineAsc() {
		return ResponseEntity.ok(this.jobAdvertisementService.getByActivatedOrderByApplicationDeadlineAsc());
	}		
	
	@GetMapping("/getallactivatedandemployerid")
	public ResponseEntity<?> getByActivatedAndEmployerId(@Valid @RequestParam(name = "id") Integer id) {
		return ResponseEntity.ok(this.jobAdvertisementService.getByActivatedAndEmployerId(id));
	}
	
	@GetMapping("/activatejobadvertisement")
	public ResponseEntity<?> activateJobAdvertisement(@Valid @RequestParam(name = "id") Integer id) {
		return ResponseEntity.ok(this.jobAdvertisementService.activateJobAdvertisement(id));
	}
	
	@GetMapping("/deactivatejobadvertisement")
	public ResponseEntity<?> deactivateJobAdvertisement(@Valid @RequestParam(name = "id") Integer id) {
		return ResponseEntity.ok(this.jobAdvertisementService.deactivateJobAdvertisement(id));
	}
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ErrorDataResult<Object> handleValidationException(MethodArgumentNotValidException exceptions) {
		Map<String, String> validationErrors = new HashMap<String, String>();
		for (FieldError fieldError: exceptions.getBindingResult().getFieldErrors()) {
			validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		return new ErrorDataResult<Object>("Validation Errors", validationErrors);
	}
	
}
